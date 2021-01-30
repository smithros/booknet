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

package com.kpi.booknet.booknet.repos;

import java.util.List;
import com.kpi.booknet.booknet.UserRole;
import com.kpi.booknet.booknet.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    User findById(long id);

    User findByName(String name);

    User findByEmail(String email);

    List<User> findAllByName(String name);

    List<User> findAllByRoleEquals(UserRole role);

    @Transactional
    @Modifying
    @Query("update User u set u.name = :name, u.password = :pwd, u.email =:email " +
        "where u.name = :name")
    void updateByName(@Param("name") String name,
                      @Param("pwd") String password,
                      @Param("email") String email);

    @Transactional
    @Modifying
    @Query("update User u set u.activated = false where u.id = :id")
    void deactivateAccount(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("update User u set u.activated = false where u.email = :email")
    void activateAccount(@Param("email") String email);
}
