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

package org.opensaml.xml.encryption.impl;

import org.opensaml.xml.XMLObject;
import org.opensaml.xml.encryption.EncryptionProperties;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.util.XMLConstants;
import org.w3c.dom.Element;

/**
 * A thread-safe Marshaller for {@link org.opensaml.xml.encryption.EncryptionProperties} objects.
 */
public class EncryptionPropertiesMarshaller extends AbstractXMLEncryptionMarshaller {
    
    /**
     * Constructor
     *
     */
    public EncryptionPropertiesMarshaller(){
        super(XMLConstants.XMLENC_NS, EncryptionProperties.DEFAULT_ELEMENT_LOCAL_NAME);
    }

    /**
     * Constructor
     *
     * @param targetNamespaceURI
     * @param targetLocalName
     */
    protected EncryptionPropertiesMarshaller(String targetNamespaceURI, String targetLocalName){
        super(targetNamespaceURI, targetLocalName);
    }

    /** {@inheritDoc} */
    protected void marshallAttributes(XMLObject xmlObject, Element domElement) throws MarshallingException {
        EncryptionProperties ep = (EncryptionProperties) xmlObject;
        
        if (ep.getID() != null) {
            domElement.setAttributeNS(null, EncryptionProperties.ID_ATTRIB_NAME, ep.getID());
            domElement.setIdAttributeNS(null, EncryptionProperties.ID_ATTRIB_NAME, true);
        }
        
    }

}
