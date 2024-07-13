/*
 * Copyright 2024 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.signalservice.api.services;

import org.signal.libsignal.attest.AttestationDataException;
import org.signal.libsignal.sgxsession.SgxCommunicationFailureException;

import java.time.Instant;
import java.util.Arrays;

public class CdsiClient {
  public long created;

  public CdsiClient(byte[] mrenclave, byte[] attestationMsg, Instant currentInstant) throws AttestationDataException {
    if (!Arrays.equals(attestationMsg, "CDSI".getBytes())) {
      throw new AttestationDataException("Bad fake");
    }

    created = currentInstant.toEpochMilli();
  }

  public byte[] initialRequest() {
    return "Client".getBytes();
  }

  public void completeHandshake(byte[] handshakeResponse) throws SgxCommunicationFailureException {
    if (!Arrays.equals(handshakeResponse, "Ready".getBytes())) {
      throw new SgxCommunicationFailureException("Bad fake");
    }
  }

  public byte[] establishedSend(byte[] plaintext) {
    return plaintext;
  }

  public byte[] establishedRecv(byte[] ciphertext) {
    return ciphertext;
  }
}
