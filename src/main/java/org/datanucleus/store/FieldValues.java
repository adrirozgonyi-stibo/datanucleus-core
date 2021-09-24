/**********************************************************************
Copyright (c) 2011 Andy Jefferson and others. All rights reserved. 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package org.datanucleus.store;

import org.datanucleus.FetchPlan;
import org.datanucleus.state.DNStateManager;

/**
 * Interface for field values.
 */
public interface FieldValues
{
    /**
     * Method to retrieve the fields and store them in the object managed by StateManager.
     * @param sm StateManager for the object
     */
    void fetchFields(DNStateManager sm);

    /**
     * Method to retrieve the unloaded fields and store them in the object managed by StateManager. 
     * @param sm StateManager for the object
     */
    void fetchNonLoadedFields(DNStateManager sm);

    /**
     * Accessor for any FetchPlan to use when loading of fields (if any).
     * @return The Fetch Plan
     */
    FetchPlan getFetchPlanForLoading();
}