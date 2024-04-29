package com.github.beltraliny.desafionexfar.utils.vo;

import com.github.beltraliny.desafionexfar.controller.dto.ReportRequestFilterDTO;
import com.github.beltraliny.desafionexfar.controller.dto.ReportRequestDTO;
import com.github.beltraliny.desafionexfar.utils.enums.ReportFormat;
import com.github.beltraliny.desafionexfar.utils.enums.ReportType;
import com.github.beltraliny.desafionexfar.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
public class ReportRequestVO {

    private static final Logger logger = Logger.getLogger(ReportRequestVO.class.getName());

    private final String REPORT_SIMPLE = "PedidoResumido";
    private final String REPORT_DETAILED = "PedidoDetalhado";

    private ReportType type;
    private ReportFormat format;
    private String cnpj;
    private Instant createdAtStartDate;
    private Instant createdAtEndDate;
    private String status;
    private Double minNetTotal;
    private Double maxNetTotal;
    private String reportName;

    public ReportRequestVO(ReportRequestDTO reportRequestDTO) {
        this.type = ReportType.convert(reportRequestDTO.key());
        this.format = ReportFormat.convert(reportRequestDTO.format());

        if (this.type != null) {
            this.reportName = this.type.isSimple() ? REPORT_SIMPLE : REPORT_DETAILED;
        }

        if (reportRequestDTO.filters() != null) {
            buildFiltersData(reportRequestDTO.filters());
        }
    }

    public void buildFiltersData(List<ReportRequestFilterDTO> reportRequestFilterDTOList) {
        try {
            for (ReportRequestFilterDTO reportRequestFilterDTO : reportRequestFilterDTOList) {
                switch (reportRequestFilterDTO.key()) {
                    case "cnpj":
                        this.cnpj = reportRequestFilterDTO.value1();
                        break;
                    case "createdAt":
                        this.createdAtStartDate = DateUtil.buildInstantFromString(reportRequestFilterDTO.value1());
                        this.createdAtEndDate = DateUtil.buildInstantFromString(reportRequestFilterDTO.value2());
                        break;
                    case "status" :
                        this.status = reportRequestFilterDTO.value1();
                        break;
                    case "netTotal":
                        if (reportRequestFilterDTO.operation().equals("GTE")) {
                            this.minNetTotal = Double.parseDouble(reportRequestFilterDTO.value1());
                        } else if (reportRequestFilterDTO.operation().equals("LTE")) {
                            this.maxNetTotal = Double.parseDouble(reportRequestFilterDTO.value1());
                        }
                        break;
                }
            }
        } catch (Exception exception) {
            logger.severe("[ReportRequestVO.buildFiltersData] >> " + exception.getMessage());
            throw exception;
        }
    }
}
