/*
 * Licensed to the Technische Universität Darmstadt under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The Technische Universität Darmstadt 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tudarmstadt.ukp.clarin.webanno.ui.monitoring.support;

import static de.tudarmstadt.ukp.clarin.webanno.model.SourceDocumentState.NEW;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.LambdaColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.tudarmstadt.ukp.clarin.webanno.model.SourceDocumentState;

public class SourceDocumentStateColumn
    extends LambdaColumn<DocumentMatrixRow, Void>
{
    private static final long serialVersionUID = -5675638452449649901L;

    public SourceDocumentStateColumn()
    {
        super(Model.of("Document"), row -> row.getSourceDocument().getState());
    }

    @Override
    public void populateItem(Item<ICellPopulator<DocumentMatrixRow>> aItem, String aComponentId,
            IModel<DocumentMatrixRow> aRowModel)
    {
        IModel<SourceDocumentState> documentState = (IModel<SourceDocumentState>) getDataModel(
                aRowModel);
        Label state = new Label(aComponentId, stateSymbol(documentState.orElse(NEW).getObject()));
        state.setEscapeModelStrings(false);
        aItem.add(state);
    }

    private String stateSymbol(SourceDocumentState aDocState)
    {
        switch (aDocState) {
        case NEW:
            return "<i class=\"far fa-circle\"></i>";
        case ANNOTATION_IN_PROGRESS:
            return "<i class=\"far fa-play-circle\"></i>";
        case ANNOTATION_FINISHED:
            return "<i class=\"far fa-check-circle\"></i>";
        case CURATION_IN_PROGRESS:
            return "<i class=\"fas fa-clipboard\"></i>";
        case CURATION_FINISHED:
            return "<i class=\"fas fa-clipboard-check\"></i>";
        }

        return "";
    }
}
