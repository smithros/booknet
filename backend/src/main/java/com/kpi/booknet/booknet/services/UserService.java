/*
 * MIT License
 *
 * Copyright (c) 2021 Koval Rostyslav
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.kpi.booknet.booknet.services;

import java.util.List;
import com.kpi.booknet.booknet.exceptions.BookNetException;
import com.kpi.booknet.booknet.exceptions.ErrorType;
import com.kpi.booknet.booknet.model.User;
import com.kpi.booknet.booknet.repos.RecoverCodeRepository;
import com.kpi.booknet.booknet.repos.UserRepository;
import com.kpi.booknet.booknet.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    /**
     * Email regex.
     */
    public static final String EMAIL_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

    /**
     * Password regex to check if it contains at least: one letter, one number, one special symbol
     * and total length is not less than 8.
     */
    public static final String PASSWORD_REGEX
        = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$_!%*#?&])[A-Za-z\\d@$!%*_#?&]{8,}$";

    private final UserRepository userRepo;
    private final RecoverCodeRepository recoverCodeRepo;

    @Autowired
    public UserService(final UserRepository userRepository,
                       final RecoverCodeRepository recoverCodeRepo
    ) {
        this.userRepo = userRepository;
        this.recoverCodeRepo = recoverCodeRepo;
    }

    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public User getById(final long id) {
        final User user = this.userRepo.findById(id);
        if (!user.isActivated()) {
            throw new BookNetException(ErrorType.USR_NOT_ACTIVATED.getMessage());
        }
        return user;
    }

    public User updateByName(final User user) {
        User currUser = this.userRepo.findByName(user.getName());
        final String password = user.getPassword();
        final String email = user.getEmail();

        if (this.emailIsValid(currUser, email)) {
            if (password.isEmpty()) {
                user.setPassword(currUser.getPassword());
                this.userRepo.updateByName(user.getName(), user.getPassword(), user.getEmail());
            } else {
                if (this.passwordIsValid(password)) {
                    // user.setPassword(bCryptPasswordEncoder.encode(password));
                    this.userRepo.updateByName(user.getName(), user.getPassword(), user.getEmail());
                } else {
                    throw new BookNetException(ErrorType.USR_PWD_NOT_VALID.getMessage());
                }
            }
            currUser = this.userRepo.findByName(user.getName());
            return currUser;
        }
        throw new BookNetException(ErrorType.USR_EMAIL_NOT_VALID.getMessage());
    }

    public User createAdmin(final User admin) {
        if (this.notExistsInBase(admin)) {
            admin.setVerified(true);
            this.userRepo.save(admin);
            return this.userRepo.findByName(admin.getName());
        }
        throw new BookNetException(ErrorType.USR_ALREADY_EXISTS.getMessage());
    }

    public User activateAccount(final String email, final String code) {
        if (this.recoverCodeRepo.findByCode(code) != null) {
            this.userRepo.activateAccount(email);
            return userRepo.findByEmail(email);
        }
        throw new BookNetException(ErrorType.NO_SUCH_RECOVER_CODE.getMessage());
    }

    public boolean deactivateAccount(final long id) {
        if (userRepo.findById(id) != null) {
            userRepo.deactivateAccount(id);
            return true;
        }
        throw new BookNetException(ErrorType.USR_NOT_FOUND.getMessage());
    }

    public List<User> searchUsersByUsername(final String search) {
        return this.userRepo.findAllByName(search.toLowerCase());
    }

    public List<User> getAllAdmins() {
        return this.userRepo.findAllByRoleEquals(UserRole.ADMIN);
    }

    public List<User> getAllModerators() {
        return this.userRepo.findAllByRoleEquals(UserRole.MODERATOR);
    }

    private boolean notExistsInBase(final User admin) {
        return this.userRepo.findByName(admin.getName()) == null &&
            this.userRepo.findByEmail(admin.getEmail()) == null;
    }

    private boolean passwordIsValid(final String password) {
        return password.matches(UserService.PASSWORD_REGEX);
    }

    private boolean emailIsValid(final User currUser, final String email) {
        return !email.isEmpty() && email.matches(UserService.EMAIL_REGEX)
            && (this.userRepo.findByEmail(email) == null || currUser.getEmail().equals(email));
    }
}
