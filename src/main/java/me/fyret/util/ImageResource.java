package me.fyret.util;

import com.vaadin.server.StreamResource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ImageResource implements StreamResource.StreamSource
{

    public ImageResource(byte[] data)
    {
        this.data = data;
    }

    @Override
    public InputStream getStream()
    {
        if (data == null)
        {
            return null;
        }
        return new ByteArrayInputStream(data);
    }
    private final byte[] data;

}
