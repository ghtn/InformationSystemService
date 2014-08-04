package com.ghtn.service;

import javax.servlet.http.HttpSession;

import com.ghtn.model.Transfer;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lihe on 14-6-23.
 */
public interface TransferManager extends GenericManager<Transfer, Integer> {

    List<Transfer> listTransferByCard( String card) throws Exception;
}
