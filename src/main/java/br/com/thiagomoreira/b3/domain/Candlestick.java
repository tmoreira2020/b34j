/**
 * Copyright © 2018 Thiago Moreira (tmoreira2020@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.thiagomoreira.b3.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Candlestick {

	private final Date date;
	private final String code;
	private int type;
	private int bdiCode;
	private double priceOpen;
	private double priceMax;
	private double priceMin;
	private double priceAverage;
	private double priceClose;
	private int totalTrades;
	private long totalQuantityStockTraded;
	private BigDecimal totalVolume;

	public Candlestick(Date date, String code) {
		super();
		this.date = date;
		this.code = code;
	}

	public int getBdiCode() {
		return bdiCode;
	}

	public String getCode() {
		return code;
	}

	public Date getDate() {
		return date;
	}

	public double getPriceAverage() {
		return priceAverage;
	}

	public double getPriceClose() {
		return priceClose;
	}

	public double getPriceMax() {
		return priceMax;
	}

	public double getPriceMin() {
		return priceMin;
	}

	public double getPriceOpen() {
		return priceOpen;
	}

	public long getTotalQuantityStockTraded() {
		return totalQuantityStockTraded;
	}

	public int getTotalTrades() {
		return totalTrades;
	}

	public BigDecimal getTotalVolume() {
		return totalVolume;
	}

	public int getType() {
		return type;
	}

	public void setBdiCode(int bdiCode) {
		this.bdiCode = bdiCode;
	}

	public void setPriceAverage(double priceAverage) {
		this.priceAverage = priceAverage;
	}

	public void setPriceClose(double priceClose) {
		this.priceClose = priceClose;
	}

	public void setPriceMax(double priceMax) {
		this.priceMax = priceMax;
	}

	public void setPriceMin(double priceMin) {
		this.priceMin = priceMin;
	}

	public void setPriceOpen(double priceOpen) {
		this.priceOpen = priceOpen;
	}

	public void setTotalQuantityStockTraded(long totalQuantityStockTraded) {
		this.totalQuantityStockTraded = totalQuantityStockTraded;
	}

	public void setTotalTrades(int totalTrades) {
		this.totalTrades = totalTrades;
	}

	public void setTotalVolume(BigDecimal totalVolume) {
		this.totalVolume = totalVolume;
	}

	public void setType(int type) {
		this.type = type;
	}
}
