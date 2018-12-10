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

package qunar.tc.qmq.consumer.pull;

import qunar.tc.qmq.base.BaseMessage;
import qunar.tc.qmq.consumer.ConsumeMessage;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * PULL模式消息
 * 
 * @author yiqun.fan create on 17-7-20.
 */
class PulledMessage extends ConsumeMessage {
    private static final long serialVersionUID = 634057708835754701L;
    /** ACK游标 */
    private transient final AckEntry ackEntry;
    /** 回复HOOK */
    private transient final AckHook ackHook;
    /** 是否已经回复 */
    private transient final AtomicBoolean hasAcked = new AtomicBoolean(false);

    PulledMessage(BaseMessage message, AckEntry ackEntry, AckHook ackHook) {
        super(message);
        this.ackEntry = ackEntry;
        this.ackHook = ackHook;
    }

    AckEntry ackEntry() {
        return ackEntry;
    }

    boolean hasNotAcked() {
        return !hasAcked.get();
    }

    @Override
    public void ack(long elapsed, Throwable e) {
        if (!hasAcked.compareAndSet(false, true)) {
            return;
        }
        if (ackHook != null) {
            ackHook.call(this, e);
        } else {
            AckHelper.ack(this, e);
        }
    }
}
