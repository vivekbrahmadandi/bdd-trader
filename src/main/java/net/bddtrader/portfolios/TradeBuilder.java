package net.bddtrader.portfolios;

import net.bddtrader.portfolios.dsl.AtAPriceOf;
import net.bddtrader.portfolios.dsl.CentsEach;
import net.bddtrader.portfolios.dsl.InCurrency;
import net.bddtrader.portfolios.dsl.SharesOf;

public class TradeBuilder implements SharesOf, AtAPriceOf, CentsEach, InCurrency {
    private final TradeType tradeType;
    private final Long numberOfShares;
    private String securityCode;
    private Long priceInCents;

    public TradeBuilder(TradeType tradeType, Long numberOfShares) {
        this.tradeType = tradeType;
        this.numberOfShares = numberOfShares;
    }

    @Override
    public AtAPriceOf sharesOf(String securityCode) {
        this.securityCode = securityCode;
        return this;
    }

    @Override
    public CentsEach at(Long priceInCents) {
        this.priceInCents = priceInCents;
        return this;
    }

    @Override
    public Trade atMarketPrice() {
        return new Trade(securityCode, tradeType, numberOfShares, 0L);
    }

    public Trade centsEach() {
        return new Trade(securityCode, tradeType, numberOfShares, priceInCents);
    }

    @Override
    public Trade dollars() {
        return new Trade(Trade.CASH_ACCOUNT, tradeType, numberOfShares * 100, 1L);
    }

    @Override
    public Trade cents() {
        return new Trade(Trade.CASH_ACCOUNT, tradeType, numberOfShares, 1L);
    }
}
