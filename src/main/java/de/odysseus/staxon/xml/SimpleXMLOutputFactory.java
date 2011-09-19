/*
 * Copyright 2011 Odysseus Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.odysseus.staxon.xml;

import java.io.OutputStream;
import java.io.Writer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import de.odysseus.staxon.AbstractXMLOutputFactory;

public class SimpleXMLOutputFactory extends AbstractXMLOutputFactory {
	@Override
	public XMLStreamWriter createXMLStreamWriter(Writer stream) throws XMLStreamException {
		return new SimpleXMLStreamWriter(stream);
	}

	@Override
	public XMLStreamWriter createXMLStreamWriter(OutputStream stream) throws XMLStreamException {
		return createXMLStreamWriter(stream, "UTF-8");
	}

	@Override
	public void setProperty(String name, Object value) throws IllegalArgumentException {
		if (XMLOutputFactory.IS_REPAIRING_NAMESPACES.equals(name)) {
			if (!getProperty(name).equals(value)) {
				throw new IllegalArgumentException("Cannot change property: " + name);
			}
		} else {
			throw new IllegalArgumentException("Unsupported property: " + name);
		}
	}

	@Override
	public Object getProperty(String name) throws IllegalArgumentException {
		if (XMLOutputFactory.IS_REPAIRING_NAMESPACES.equals(name)) {
			return Boolean.FALSE;
		} else {
			throw new IllegalArgumentException("Unsupported property: " + name);
		}
	}

	@Override
	public boolean isPropertySupported(String name) {
		if (XMLOutputFactory.IS_REPAIRING_NAMESPACES.equals(name)) {
			return true;
		} else {
			return false;
		}
	}
}
