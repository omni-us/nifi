/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.nifi.cluster.coordination.http.endpoints;

import org.apache.nifi.cluster.manager.DocumentedTypesMerger;
import org.apache.nifi.cluster.protocol.NodeIdentifier;
import org.apache.nifi.web.api.dto.DocumentedTypeDTO;
import org.apache.nifi.web.api.entity.ControllerServiceTypesEntity;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public class ControllerServiceTypesEndpointMerger extends AbstractNodeStatusEndpoint<ControllerServiceTypesEntity, Set<DocumentedTypeDTO>> {
    public static final String CONTROLLER_SERVICE_TYPES_URI_PATTERN = "/nifi-api/flow/controller-service-types";

    @Override
    public boolean canHandle(URI uri, String method) {
        return "GET".equalsIgnoreCase(method) && CONTROLLER_SERVICE_TYPES_URI_PATTERN.equals(uri.getPath());
    }

    @Override
    protected Class<ControllerServiceTypesEntity> getEntityClass() {
        return ControllerServiceTypesEntity.class;
    }

    @Override
    protected Set<DocumentedTypeDTO> getDto(ControllerServiceTypesEntity entity) {
        return entity.getControllerServiceTypes();
    }

    @Override
    protected void mergeResponses(Set<DocumentedTypeDTO> clientDto, Map<NodeIdentifier, Set<DocumentedTypeDTO>> dtoMap, NodeIdentifier selectedNodeId) {
        DocumentedTypesMerger.mergeDocumentedTypes(clientDto, dtoMap);
    }
}
