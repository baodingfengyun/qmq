/*
 * Copyright 2018 Qunar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.com.qunar.pay.trade.api.card.service.usercard.UserCardQueryFacade
 */

package qunar.tc.qmq.base;

import java.util.HashSet;
import java.util.Set;

/**
 * 消息头
 * 
 * @author yiqun.fan create on 17-7-4.
 */
public class MessageHeader {
    /** 消息体数据CRC校验 */
    private long bodyCrc;
    /** 消息标记,参见Flags类,标记:isDelay */
    private byte flag;
    /** 创建时间 */
    private long createTime;
    /** 过期时间 */
    private long expireTime;
    /** 分类 */
    private String subject;
    /** 消息ID */
    private String messageId;
    /** TAG集合 */
    private Set<String> tags = new HashSet<>();

    public long getBodyCrc() {
        return bodyCrc;
    }

    public void setBodyCrc(long bodyCrc) {
        this.bodyCrc = bodyCrc;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
