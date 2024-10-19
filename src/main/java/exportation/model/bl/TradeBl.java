package exportation.model.bl;

import exportation.controller.exception.NoTradeFoundException;
import lombok.Getter;
import exportation.model.da.TradeDa;
import exportation.model.entity.Trade;
import exportation.model.tools.CRUD;

import java.util.List;

public class TradeBl implements CRUD<Trade> {
    @Getter
    private static TradeBl tradeBl = new TradeBl();

    private TradeBl() {
    }

    //save
    @Override
    public Trade save(Trade trade) throws Exception {
        try (TradeDa tradeDa = new TradeDa()) {
            tradeDa.save(trade);
            return trade;
        }
    }

    //edit
    @Override
    public Trade edit(Trade trade) throws Exception {
        try (TradeDa tradeDa = new TradeDa()) {
            if (tradeDa.findById(trade.getId()) != null) {
                tradeDa.edit(trade);
                return trade;
            } else {
                throw new NoTradeFoundException();
            }
        }
    }

    //remove
    @Override
    public Trade remove(int id) throws Exception {
        try (TradeDa tradeDa = new TradeDa()) {
            Trade trade = tradeDa.findById(id);
            if (trade != null) {
                tradeDa.remove(id);
                return trade;
            } else {
                throw new NoTradeFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Trade> findAll() throws Exception {
        try (TradeDa tradeDa = new TradeDa()) {
            List<Trade> tradeList = tradeDa.findAll();
            if (!tradeList.isEmpty()) {
                return tradeList;
            } else {
                throw new NoTradeFoundException();
            }
        }
    }

    //findById
    @Override
    public Trade findById(int id) throws Exception {
        try (TradeDa tradeDa = new TradeDa()) {
            Trade trade = tradeDa.findById(id);
            if (trade != null) {
                return trade;
            } else {
                throw new NoTradeFoundException();
            }
        }
    }
}
