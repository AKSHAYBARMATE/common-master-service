    package com.commonmaster.controller;

    import com.commonmaster.entity.CommonMaster;
    import com.commonmaster.reponse.StandardResponse;
    import com.commonmaster.service.CommonMasterService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1/common-master")
    public class CommonMasterController {
        private final CommonMasterService commonMasterService;

        public CommonMasterController(CommonMasterService commonMasterService) {
            this.commonMasterService = commonMasterService;
        }

        @GetMapping("/{key}")
        public ResponseEntity<StandardResponse<?>> getCommonMasterData(@PathVariable String key) {
            List<CommonMaster> records = commonMasterService.getActiveCommonMastersByKey(key);
            if (records == null || records.isEmpty()) {
                StandardResponse<Void> response = StandardResponse.error(
                    "No records found for key: " + key,
                    "NOT_FOUND",
                    null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            StandardResponse<List<CommonMaster>> response = StandardResponse.success(records, "Records fetched successfully");
            return ResponseEntity.ok(response);
        }
    }
