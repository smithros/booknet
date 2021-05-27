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

package com.kpi.booknet.booknet.exceptions;

public enum ErrorType {
    USR_NOT_ACTIVATED("This user is not activated"),
    USR_PWD_NOT_VALID("User password is not valid"),
    USR_EMAIL_NOT_VALID("User email is not valid"),
    USR_ALREADY_EXISTS("User already exists"),
    USR_NOT_FOUND("User with such id not found"),
    USR_PWD_NOT_CORRECT("User password is not correct"),
    USR_NOT_ACTIVATED_OR_IS_ABSENT("User is absent or not activated"),
    NO_SUCH_RECOVER_CODE("There is no such recover code"),
    NO_SUCH_ANNOUNCEMENT("No announcement with such a id"),
    EMPTY_CREDENTIALS("Login or password or email is empty"),
    NO_BOOK_WITH_SUCH_ID("Book with such id not found"),
    NO_REVIEW_WITH_SUCH_ID("Review with such id not found");

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
