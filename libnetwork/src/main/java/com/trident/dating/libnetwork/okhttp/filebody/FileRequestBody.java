package com.trident.dating.libnetwork.okhttp.filebody;

import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * Created by android_ls on 2017/10/24.
 */

public class FileRequestBody extends RequestBody {

    private File mFile;

    public FileRequestBody(File file) {
        mFile = file;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("application/octet-stream");
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Source source = Okio.source(mFile);
        Buffer buffer = new Buffer();
        long current = 0;
        long readCount;
        while ((readCount = source.read(buffer, 1024)) != -1) {
            sink.write(buffer, readCount);
            current += readCount;
            onProgress(mFile.getName(), current, contentLength());
        }
        sink.flush();
    }

    public void onProgress(String filename, long current, long total) {

    }

}
