package com.tema1.common;

import com.tema1.goods.Goods;
import java.util.Map;
import java.util.stream.Collectors;
import com.tema1.goods.GoodsType;


public final class Constants {
    private Constants() {
        // just to trick checkstyle ;)
    }

    // Cel mai profitabil bun Ilegal din mapa.

    public static Goods getBestIllegal(final Map<Goods, Integer> mapp) {
        var result = mapp.entrySet()
                .stream()
                .filter((e) -> e.getKey().getType() == GoodsType.Illegal)
                .collect(Collectors.toList());
        int max = 0;
        var bestIllegal = result.get(0).getKey();
        for (Map.Entry<Goods, Integer> goodsIntegerEntry : result) {
            if (goodsIntegerEntry.getKey().getProfit() > max) {
                bestIllegal = goodsIntegerEntry.getKey();
                max = goodsIntegerEntry.getKey().getProfit();
            }
        }
        return bestIllegal;
    }
}
