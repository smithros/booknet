/*
 * MIT License
 *
 * Copyright (c) 2020-2021 Rostyslav Koval
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
 */

package com.kpi.booknet.booknet.controllers;

import java.util.List;
import java.util.Optional;
import com.kpi.booknet.booknet.model.User;
import com.kpi.booknet.booknet.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
@AllArgsConstructor
public final class UserController {

    private final UserService service;

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseEntity<User> update(
        @RequestParam(name = "login") final String login,
        @RequestParam(name = "newPassword") final String pass,
        @RequestParam(name = "newEmail") final String email
    ) {
        final User usr = User.builder()
            .name(login)
            .password(pass)
            .email(email)
            .build();
        return Optional.ofNullable(this.service.updateByName(usr))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> get(@PathVariable(value = "id") final long id) {
        return Optional.ofNullable(this.service.getById(id))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create/admin")
    public ResponseEntity<User> createAdmin(@RequestBody final User admin) {
        return Optional.ofNullable(this.service.createAdmin(admin))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activate")
    public ResponseEntity<User> activate(
        @RequestParam(name = "email") final String email,
        @RequestParam(name = "code") final String code
    ) {
        return Optional.ofNullable(this.service.activateAccount(email, code))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/deactivate")
    public ResponseEntity<?> deactivateAccount(@PathVariable(value = "id") final long id) {
        return this.service.deactivateAccount(id)
            ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchName}")
    public ResponseEntity<List<User>> searchUsers(
        @PathVariable(value = "searchName") final String search
    ) {
        final List<User> response = this.service.searchUsersByUsername(search);
        return response.isEmpty()
            ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public List<User> getAllUsers() {
        return this.service.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allAdmins")
    public List<User> getAdmins() {
        return this.service.getAllAdmins();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allModer")
    public List<User> getModerators() {
        return this.service.getAllModerators();
    }
}
