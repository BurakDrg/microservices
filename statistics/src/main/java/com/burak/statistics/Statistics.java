package com.burak.statistics;

import java.time.Month;

public record Statistics (Month month,
                          Integer totalOrderCount,
                          Integer totalBookCount,
                          Double totalPurchasedAmount){
}
