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

import com.kpi.booknet.booknet.exceptions.BookNetException;
import com.kpi.booknet.booknet.exceptions.ErrorType;
import com.kpi.booknet.booknet.model.User;
import com.kpi.booknet.booknet.repos.UserRepository;
import com.kpi.booknet.booknet.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthorizationService(final UserRepository userRepository/*,
                                final BCryptPasswordEncoder bCryptPasswordEncoder*/
    ) {
        this.userRepository = userRepository;
        /*this.bCryptPasswordEncoder = bCryptPasswordEncoder;*/
    }

    public User authorize(final String login, final String password) {
        if (!login.isEmpty() && !password.isEmpty()) {
            final User user = userRepository.findByName(login);
            if (user != null && user.isActivated()) {
                //if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                if (password.matches(user.getPassword())) {
                    return user;
                } else {
                    throw new BookNetException(ErrorType.USR_PWD_NOT_CORRECT.getMessage());
                }
                // }
            }
            throw new BookNetException(ErrorType.USR_NOT_ACTIVATED_OR_IS_ABSENT.getMessage());
        }
        throw new BookNetException(ErrorType.EMPTY_CREDENTIALS.getMessage());
    }

    public User register(final String login, final String password, final String email) {
        if (!login.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            if (this.userRepository.findByName(login) == null
                && this.userRepository.findByEmail(email) == null) {
                final User user = User.builder()
                    .name(login)
                    .password(password)
                    .email(email)
                    .role(UserRole.USER)
                    .build();
                this.userRepository.save(user);
                return this.userRepository.findByName(login);
            }
        }
        throw new BookNetException(ErrorType.EMPTY_CREDENTIALS.getMessage());
    }
}
