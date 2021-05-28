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

package com.kpi.booknet.booknet.repos;

import java.util.Date;
import java.util.List;
import com.kpi.booknet.booknet.model.Announcement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {

    Announcement findById(long id);

    Announcement deleteById(long id);

    List<Announcement> findAnnouncementsByStatusTrue();

    List<Announcement> findAnnouncementsByStatusFalse();

    @Transactional
    @Modifying
    @Query(value = "update announcement set status = true where announcement_id = :id",
        nativeQuery = true)
    void publish(long id);

    @Transactional
    @Modifying
    @Query(value = "update announcement set description = :desc, date = :date, status = :status, "
        + "book_id = :bid, admin_id = :aid, owner_id =:oid "
        + "where announcement_id = :entid", nativeQuery = true)
    void updateById(String desc, Date date, boolean status, long bid,
                    long aid, long oid, long entid);

}
