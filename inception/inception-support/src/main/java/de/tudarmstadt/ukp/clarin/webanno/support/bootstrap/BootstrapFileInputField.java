package de.tudarmstadt.ukp.clarin.webanno.support.bootstrap;

import java.util.List;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.FileInputConfig;

public class BootstrapFileInputField
    extends
    de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.BootstrapFileInputField
{
    private static final long serialVersionUID = 7152684712265476472L;

    public BootstrapFileInputField(String aId, IModel<List<FileUpload>> aModel,
            FileInputConfig aConfig)
    {
        super(aId, aModel, aConfig);
        initConfig(getConfig());
    }

    public BootstrapFileInputField(String aId, IModel<List<FileUpload>> aModel)
    {
        super(aId, aModel);
        initConfig(getConfig());
    }

    public BootstrapFileInputField(String aId)
    {
        super(aId);
        initConfig(getConfig());
    }

    @Override
    public void renderHead(final IHeaderResponse response)
    {
        // Workaround https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/957
        IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
        response.render(
                JavaScriptReferenceHeaderItem.forReference(settings.getJsResourceReference()));
        super.renderHead(response);
    }

    public static void initConfig(FileInputConfig aConfig)
    {
        if (!aConfig.contains(FileInputConfig.BrowseIcon)) {
            aConfig.browseIcon("<i class=\"fas fa-folder-open\"></i>");
        }

        if (!aConfig.contains(FileInputConfig.RemoveIcon)) {
            aConfig.removeIcon("<i class=\"fas fa-trash\"></i>");
        }

        if (!aConfig.contains(FileInputConfig.UploadIcon)) {
            aConfig.uploadIcon("<i class=\"fas fa-upload\"></i>");
        }

        if (!aConfig.contains(FileInputConfig.CancelIcon)) {
            aConfig.cancelIcon("<i class=\"fas fa-ban\"></i>");
        }
    }
}
