/*
 * Copyright [2006] [University Corporation for Advanced Internet Development, Inc.]
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

package org.opensaml.xml.signature.impl;

import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.signature.KeyInfoType;
import org.w3c.dom.Attr;

/**
 * A thread-safe Unmarshaller for {@link org.opensaml.xml.signature.KeyInfoType} objects.
 */
public class KeyInfoTypeUnmarshaller extends AbstractXMLSignatureUnmarshaller {

    /**
     * Constructor
     *
     * @param targetNamespaceURI
     * @param targetLocalName
     */
    protected KeyInfoTypeUnmarshaller(String targetNamespaceURI, String targetLocalName){
        super(targetNamespaceURI, targetLocalName);
    }

    /** {@inheritDoc} */
    protected void processAttribute(XMLObject xmlObject, Attr attribute) throws UnmarshallingException {
        KeyInfoType keyInfo = (KeyInfoType) xmlObject;
        
        if (attribute.getLocalName().equals(KeyInfoType.ID_ATTRIB_NAME)) {
            keyInfo.setID(attribute.getValue());
            attribute.getOwnerElement().setIdAttributeNode(attribute, true);
        } else {
            super.processAttribute(xmlObject, attribute);
        }
    }

    /** {@inheritDoc} */
    protected void processChildElement(XMLObject parentXMLObject, XMLObject childXMLObject) throws UnmarshallingException {
        KeyInfoType keyInfo = (KeyInfoType) parentXMLObject;
        
        // KeyInfoType contains a range of specific types, but also
        // support <any>, with an unbounded choice over all (no ordering)
        // so no need to distinguish.
        keyInfo.getXMLObjects().add(childXMLObject);
    }

}
