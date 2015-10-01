/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jenkinsci.plugins.fabric8.rest;

import hudson.Extension;
import org.jenkinsci.plugins.fabric8.support.JSONHelper;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 */
@Extension
public class WorkflowJobAction extends ActionSupport<WorkflowJob> {
    public static String getUrl(WorkflowJob node) {
        StaplerRequest currentRequest = Stapler.getCurrentRequest();
        String path = currentRequest.getContextPath();
        if (!path.endsWith("/")) {
            path += "/";
        }
        return path + "fabric8";
    }

    @Override
    public Class<WorkflowJob> type() {
        return WorkflowJob.class;
    }

    public Object doIndex() {
        return JSONHelper.jsonResponse(getTarget());
    }


    @Nonnull
    @Override
    public Collection<WorkflowJobAction> createFor(WorkflowJob target) {
        try {
            WorkflowJobAction action = getClass().newInstance();
            action.setTarget(target);
            List<WorkflowJobAction> answer = new ArrayList<WorkflowJobAction>();
            answer.add(action);
            return answer;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String getUrlName() {
        return "fabric8";
    }


}