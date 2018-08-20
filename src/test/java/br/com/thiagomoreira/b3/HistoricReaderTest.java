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
package br.com.thiagomoreira.b3;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import br.com.thiagomoreira.b3.domain.Candlestick;

public class HistoricReaderTest {

	@Test
	public void testReadYearFile() throws Exception {
		URL url = new URL("file:target/test-classes/COTAHIST_A2000.ZIP");

		HistoricReader reader = new HistoricReader();

		List<Candlestick> candlesticks = reader.read(url);

		assertEquals(153682, candlesticks.size());
	}

	@Test
	public void testReadDayFile() throws Exception {
		URL url = new URL("file:target/test-classes/COTAHIST_D15082018.ZIP");

		HistoricReader reader = new HistoricReader();

		List<Candlestick> candlesticks = reader.read(url);

		assertEquals(2533, candlesticks.size());
	}

	@Test
	public void testConvertSingleLine() throws Exception {
		String line = "012018081502UNIP6       010UNIPAR      PNB          R$  000000000468000000000047390000000004551000000000464200000000045660000000004566000000000457001916000000000000412500000000001915221900000000000000009999123100000010000000000000BRUNIPACNPB8187";

		HistoricReader reader = new HistoricReader();

		Candlestick candlestick = reader.convert(line);

		assertEquals("UNIP6", candlestick.getCode());
		assertEquals(46.80, candlestick.getPriceOpen(), 0);
		assertEquals(45.51, candlestick.getPriceMin(), 0);
		assertEquals(46.42, candlestick.getPriceAverage(), 0);
		assertEquals(47.39, candlestick.getPriceMax(), 0);
		assertEquals(45.66, candlestick.getPriceClose(), 0);
	}

	protected File getFile(File folderName, String fileName) {
		File file = new File(folderName, fileName + ".TXT");
		if (!file.exists()) {
			file = new File(folderName, fileName);
		}
		if (!file.exists()) {
			fileName = fileName.replace('_', '.');
			file = new File(folderName, fileName);
		}

		return file;
	}
}
