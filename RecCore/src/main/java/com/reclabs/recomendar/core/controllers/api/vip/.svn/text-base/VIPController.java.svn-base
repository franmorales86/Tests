/**
 * Project: RecCore
 * Created by: raulanatol at 07/01/14 07:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.vip;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.model.services.items.ItemService;
import com.reclabs.recomendar.objects.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/vip/")
public class VIPController {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "promoteVIP/{itemId}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public ResponseDTO promoteVIP(@PathVariable("itemId") String itemId, @RequestBody Date promoteDate) {
        ParameterHelper.assertEmpty(itemId);
        ParameterHelper.assertDateAfterNow(promoteDate, DatePrecisionType.DAY);
        itemService.promoteVIP(itemId, promoteDate);
        return ResponseDTO.OK;
    }
}
