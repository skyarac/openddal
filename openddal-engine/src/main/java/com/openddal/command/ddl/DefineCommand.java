/*
 * Copyright 2014-2016 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.openddal.command.ddl;

import com.openddal.command.Prepared;
import com.openddal.engine.Session;
import com.openddal.excutor.Executor;
import com.openddal.result.ResultInterface;

/**
 * This class represents a non-transaction statement, for example a CREATE or
 * DROP.
 */
public abstract class DefineCommand extends Prepared {

    /**
     * The transactional behavior. The default is disabled, meaning the command
     * commits an open transaction.
     */
    protected boolean transactional;

    /**
     * Create a new command for the given session.
     *
     * @param session the session
     */
    DefineCommand(Session session) {
        super(session);
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public ResultInterface queryMeta() {
        return null;
    }

    @Override
    public boolean isTransactional() {
        return transactional;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    public Executor getExecutor() {
        return null;
    }

    @Override
    public int update() {
        Executor executor = getExecutor();
        if (executor != null) {
            return executor.update();
        }
        return super.update();
    }

    @Override
    public String explainPlan() {
        Executor executor = getExecutor();
        if (executor != null) {
            return executor.explain();
        }
        return super.explainPlan();
    }

}
