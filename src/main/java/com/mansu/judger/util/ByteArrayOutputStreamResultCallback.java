package com.mansu.judger.util;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamResultCallback extends ResultCallback.Adapter<Frame> {
    private ByteArrayOutputStream stdout;
    private ByteArrayOutputStream stderr;

    public ByteArrayOutputStreamResultCallback(ByteArrayOutputStream outputStream, ByteArrayOutputStream errorStream) {
        this.stdout = outputStream;
        this.stderr = errorStream;
    }

    @Override
    public void onNext(Frame frame) {
        if (frame != null) {
            try {
                switch (frame.getStreamType()) {
                    case STDOUT:
                    case RAW:
                        if (stdout != null) {
                            stdout.write(frame.getPayload());
                            stdout.flush();
                        }
                        break;
                    case STDERR:
                        if (stderr != null) {
                            stderr.write(frame.getPayload());
                            stderr.flush();
                        }
                    break;
				default:
//					POINT: for STDIN case. (useless. just catch the warning.)
					break;
                }
            } catch (IOException e) {
                onError(e);
            }
        }
    }
}
