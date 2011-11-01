/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eel.kitchen.jsonschema.syntax;

import eel.kitchen.jsonschema.context.ValidationContext;
import eel.kitchen.util.NodeType;
import eel.kitchen.util.RhinoHelper;

import java.util.Iterator;

public final class PatternPropertiesValidator
    extends SyntaxValidator
{
    public PatternPropertiesValidator(final ValidationContext context)
    {
        super(context, "patternProperties", NodeType.OBJECT);
    }

    @Override
    protected void checkFurther()
    {
        final Iterator<String> iterator = node.getFieldNames();

        String field;

        while (iterator.hasNext()) {
            field = iterator.next();
            if (!RhinoHelper.regexIsValid(field))
                report.addMessage("invalid regex " + field);
            if (!node.get(field).isObject())
                report.addMessage("value is not a schema");
        }
    }
}
