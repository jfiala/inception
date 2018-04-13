/*
 * Copyright 2018
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
package de.tudarmstadt.ukp.inception.kb.graph;

import java.io.Serializable;

import org.cyberborean.rdfbeans.datatype.DatatypeMapper;
import org.cyberborean.rdfbeans.datatype.DefaultDatatypeMapper;
import org.eclipse.rdf4j.model.BNode;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;

public class KBQualifier
    implements Serializable
{
    private static final long serialVersionUID = 4648563545691138244L;

    private KBStatement kbStatement;

    private String language;

    private KBHandle kbProperty;

    private Object value;

    public KBQualifier(KBStatement kbStatement, KBHandle kbProperty, Object aValue)
    {
        this.kbStatement = kbStatement;
        this.kbProperty = kbProperty;
        DatatypeMapper mapper = new DefaultDatatypeMapper();

        if (aValue instanceof Literal) {
            Literal litValue = (Literal) aValue;
            language = litValue.getLanguage().orElse(null);
            value = mapper.getJavaObject(litValue);
        }
        else if (aValue instanceof IRI) {
            value = aValue;
        }
        else if (aValue instanceof BNode) {
            value = null;
        }
        else {
            throw new IllegalStateException("Unknown object type: " + aValue.getClass());
        }
    }

    public KBQualifier(KBStatement kbStatement)
    {
        this.kbStatement = kbStatement;
    }

    public KBStatement getKbStatement()
    {
        return kbStatement;
    }

    public void setKbStatement(KBStatement kbStatement)
    {
        this.kbStatement = kbStatement;
    }

    public KBHandle getKbProperty()
    {
        return kbProperty;
    }

    public void setKbProperty(KBHandle kbProperty)
    {
        this.kbProperty = kbProperty;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String aLanguage) {
        language = language;
    }
}
