/**********************************************************************
Copyright (c) 2007 Andy Jefferson and others. All rights reserved.
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
package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.DNStateManager;
import org.datanucleus.store.types.scostore.CollectionStore;
import org.datanucleus.store.types.scostore.Store;
import org.datanucleus.util.StringUtils;

/**
 * Add operation for a collection where we have a backing store.
 */
public class CollectionAddOperation<E> implements SCOOperation
{
    final DNStateManager sm;
    final int fieldNumber;
    final CollectionStore<E> store;

    /** The value to add. */
    final E value;

    public CollectionAddOperation(DNStateManager sm, CollectionStore<E> store, E value)
    {
        this.sm = sm;
        this.fieldNumber = store.getOwnerMemberMetaData().getAbsoluteFieldNumber();
        this.store = store;
        this.value = value;
    }

    public CollectionAddOperation(DNStateManager sm, int fieldNum, E value)
    {
        this.sm = sm;
        this.fieldNumber = fieldNum;
        this.store = null;
        this.value = value;
    }

    /* (non-Javadoc)
     * @see org.datanucleus.flush.SCOOperation#getMemberMetaData()
     */
    @Override
    public AbstractMemberMetaData getMemberMetaData()
    {
        return store != null ? store.getOwnerMemberMetaData() : sm.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
    }

    /**
     * Accessor for the value being added.
     * @return Value being added
     */
    public E getValue()
    {
        return value;
    }

    /**
     * Perform the add(Object) operation to the backing store.
     */
    public void perform()
    {
        if (store != null)
        {
            // TODO If this value is the detached object and we are using attachCopy this needs swapping for the attached object
            store.add(sm, value, -1);
        }
    }

    public Store getStore()
    {
        return store;
    }

    /* (non-Javadoc)
     * @see org.datanucleus.flush.Operation#getStateManager()
     */
    public DNStateManager getStateManager()
    {
        return sm;
    }

    public String toString()
    {
        return "COLLECTION ADD : " + sm + " field=" + getMemberMetaData().getName() + " value=" + StringUtils.toJVMIDString(value);
    }
}