package org.isda.cdm.functions;

import com.opengamma.strata.basics.schedule.Frequency;
import com.opengamma.strata.basics.schedule.RollConvention;

import cdm.product.common.schedule.CalculationPeriodDates;

class CdmToStrataMapper {

    static Frequency getFrequency(CalculationPeriodDates calculationPeriodDates) {
        return Frequency.parse(calculationPeriodDates.getCalculationPeriodFrequency().getPeriodMultiplier().toString() + calculationPeriodDates.getCalculationPeriodFrequency().getPeriod().toString());
    }

    //
    static RollConvention getRollConvention(CalculationPeriodDates calculationPeriodDates) {
            String rollConventionName = calculationPeriodDates.getCalculationPeriodFrequency().getRollConvention().toString();
            // The display name of the match RollConvention using FpML
            return RollConvention.extendedEnum().externalNames("FpML").lookup(rollConventionName);


    }

}
