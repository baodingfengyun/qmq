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

package qunar.tc.qmq;

import java.util.Collections;
import java.util.Set;

/**
 * 消费者（订阅者）订阅参数。定义此类而不是采用Map<String,Object>类型的原因是，参数固定，更容易理解。
 * 
 * @author yiqun.fan create on 17-11-2.
 */
public class SubscribeParam {
    public static final SubscribeParam DEFAULT = new SubscribeParam(false, false, TagType.NO_TAG,
            Collections.<String>emptySet());

    /** 是否最多可以消费一次（1次或0次），是：针对不太重要的消息 */
    private final boolean consumeMostOnce;
    /** 标签之间的关系（或、并） */
    private final TagType tagType;
    /** 是否广播 */
    private boolean isBroadcast;
    /** 添加的标签 */
    private final Set<String> tags;

    private SubscribeParam(boolean consumeMostOnce, boolean isBroadcast, TagType tagType, Set<String> tags) {
        this.consumeMostOnce = consumeMostOnce;
        this.isBroadcast = isBroadcast;
        this.tags = tags;
        this.tagType = tagType;
    }

    public boolean isConsumeMostOnce() {
        return consumeMostOnce;
    }

    public Set<String> getTags() {
        return tags;
    }

    public TagType getTagType() {
        return tagType;
    }

    public boolean isBroadcast() {
        return isBroadcast;
    }

    public void setBroadcast(boolean isBroadcast) {
        this.isBroadcast = isBroadcast;
    }

    public static final class SubscribeParamBuilder {
        private boolean consumeMostOnce = false;
        private Set<String> tags = Collections.emptySet();
        private TagType tagType = TagType.NO_TAG;
        private boolean isBroadcast;

        public SubscribeParam create() {
            return new SubscribeParam(consumeMostOnce, isBroadcast, tagType, tags);
        }

        public SubscribeParamBuilder setConsumeMostOnce(boolean consumeMostOnce) {
            this.consumeMostOnce = consumeMostOnce;
            return this;
        }

        public SubscribeParamBuilder setTagType(final TagType tagType) {
            if (tagType != null) {
                this.tagType = tagType;
            }
            return this;
        }

        public SubscribeParamBuilder setTags(Set<String> tags) {
            if (tags != null && tags.size() != 0) {
                this.tags = tags;
            }
            return this;
        }

        public SubscribeParamBuilder setBroadcast(boolean isBroadcast) {
            this.isBroadcast = isBroadcast;
            return this;
        }
    }
}
