/*
 * Copyright 2009 University Corporation for Advanced Internet Development, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.ws.wsaddressing.impl;

import org.opensaml.ws.wsaddressing.AttributedUnsignedLong;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Element;

/**
 * Marshaller for instances of {@link AttributedUnsignedLong}.
 */
public class AttributedUnsignedLongMarshaller extends AbstractWSAddressingObjectMarshaller {

    /** {@inheritDoc} */
    protected void marshallAttributes(XMLObject xmlObject, Element domElement) throws MarshallingException {
        AttributedUnsignedLong aul = (AttributedUnsignedLong) xmlObject;
        XMLHelper.marshallAttributeMap(aul.getUnknownAttributes(), domElement);
    }

    /** {@inheritDoc} */
    protected void marshallElementContent(XMLObject xmlObject, Element domElement) throws MarshallingException {
        AttributedUnsignedLong aul = (AttributedUnsignedLong) xmlObject;
        if (aul.getValue() != null) {
            XMLHelper.appendTextContent(domElement, aul.getValue().toString());
        }
    }

}
