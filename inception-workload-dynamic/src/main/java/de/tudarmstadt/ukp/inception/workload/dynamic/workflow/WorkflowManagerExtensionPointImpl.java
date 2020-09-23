/*
 * Copyright 2020
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tudarmstadt.ukp.inception.workload.dynamic.workflow;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import de.tudarmstadt.ukp.clarin.webanno.model.Project;
import de.tudarmstadt.ukp.clarin.webanno.support.extensionpoint.ExtensionPoint_ImplBase;
import de.tudarmstadt.ukp.inception.workload.config.WorkloadManagementAutoConfiguration;

/**
 * <p>
 * This class is exposed as a Spring Component via
 * {@link WorkloadManagementAutoConfiguration#workloadExtensionPoint}.
 * </p>
 */
public class WorkflowManagerExtensionPointImpl
    extends ExtensionPoint_ImplBase<Project, WorkflowManagerExtension>
    implements WorkflowManagerExtensionPoint
{

    @Autowired
    public WorkflowManagerExtensionPointImpl(
        List<WorkflowManagerExtension> aExtensions)
    {
        super(aExtensions);
    }

    @Override
    public List<WorkflowManagerExtension> getExtensions(Project aContext)
    {
        Map<String, WorkflowManagerExtension> byRole = new LinkedHashMap<>();
        for (WorkflowManagerExtension extension : super.getExtensions(aContext)) {
            byRole.put(extension.getId(), extension);
        }
        return new ArrayList<>(byRole.values());
    }

    @Override
    public WorkflowManagerExtension getDefault()
    {
        return getExtensions().get(0);
    }
    
    @Override
    public List<WorkflowManagerType> getTypes()
    {
        return getExtensions().stream()
                .map(manExt -> new WorkflowManagerType(manExt.getId(), manExt.getLabel()))
                .collect(toList());
    }
}
