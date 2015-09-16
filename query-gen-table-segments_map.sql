SELECT
	ID,
	CASE
		WHEN lower(SPV_6B) LIKE 'once a month' THEN 6
		WHEN lower(SPV_6B) LIKE 'not at all' THEN 0
		WHEN lower(SPV_6B) LIKE 'once /twice a yr' THEN 4
		WHEN lower(SPV_6B) LIKE 'once a year' THEN 2
		WHEN lower(SPV_6B) LIKE 'once a day' THEN 10
		WHEN lower(SPV_6B) LIKE 'once a week' THEN 8
		WHEN lower(SPV_6B) LIKE 'don''t know' THEN null 
		WHEN lower(SPV_6B) LIKE 'n/s' THEN null 
		WHEN lower(SPV_6B) LIKE 'n/a' THEN null 
		WHEN lower(SPV_6B) LIKE 'refusal' THEN null 
		WHEN lower(SPV_6B) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPV_6B) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPV_6B) LIKE 'not required' THEN null 
	END AS SPV_6B,
	CASE
		WHEN lower(GEODPMF) LIKE 'york regional h.' THEN 8
		WHEN lower(GEODPMF) LIKE 'sunrise/kelsey' THEN 4
		WHEN lower(GEODPMF) LIKE 'haliburton-kaw.' THEN 2
		WHEN lower(GEODPMF) LIKE 'région de laval' THEN 6
		WHEN lower(GEODPMF) LIKE 'pei' THEN 8
		WHEN lower(GEODPMF) LIKE 'rég. outaouais' THEN 5
		WHEN lower(GEODPMF) LIKE 'rég.montreal' THEN 9
		WHEN lower(GEODPMF) LIKE 'colchester east' THEN 0
		WHEN lower(GEODPMF) LIKE 'sudbury,dist. h.' THEN 3
		WHEN lower(GEODPMF) LIKE 'rég. saguenay' THEN 6
		WHEN lower(GEODPMF) LIKE 'rég. lanaudiaire' THEN 6
		WHEN lower(GEODPMF) LIKE 'rég bas-st laur,' THEN 5
		WHEN lower(GEODPMF) LIKE 'richmond' THEN 2
		WHEN lower(GEODPMF) LIKE 'rég. gaspésie' THEN 4
		WHEN lower(GEODPMF) LIKE 'edmonton zone' THEN 9
		WHEN lower(GEODPMF) LIKE 'fraser south' THEN 7
		WHEN lower(GEODPMF) LIKE 'chatham-kent u.' THEN 1
		WHEN lower(GEODPMF) LIKE 'wellington-duf-g' THEN 5
		WHEN lower(GEODPMF) LIKE 'central zone' THEN 9
		WHEN lower(GEODPMF) LIKE 'rég. montérégie' THEN 9
		WHEN lower(GEODPMF) LIKE 'yukon/northwest' THEN 9
		WHEN lower(GEODPMF) LIKE 'south west nova' THEN 1
		WHEN lower(GEODPMF) LIKE 'south zone' THEN 8
		WHEN lower(GEODPMF) LIKE 'northwest' THEN 0
		WHEN lower(GEODPMF) LIKE 'grey b. hlth un.' THEN 3
		WHEN lower(GEODPMF) LIKE 'southen health' THEN 6
		WHEN lower(GEODPMF) LIKE 'vancouver' THEN 7
		WHEN lower(GEODPMF) LIKE 'eastern ont. h.u' THEN 4
		WHEN lower(GEODPMF) LIKE 'durham reg. hlth' THEN 8
		WHEN lower(GEODPMF) LIKE 'city hamilton h.' THEN 7
		WHEN lower(GEODPMF) LIKE 'haldimand-norf h' THEN 0
		WHEN lower(GEODPMF) LIKE 'renfrew county h' THEN 1
		WHEN lower(GEODPMF) LIKE 'pictou county' THEN 0
		WHEN lower(GEODPMF) LIKE 'rég. estrie' THEN 5
		WHEN lower(GEODPMF) LIKE 'rég. mauricie' THEN 8
		WHEN lower(GEODPMF) LIKE 'v. district h.' THEN 0
		WHEN lower(GEODPMF) LIKE 'kootenay-bound.' THEN 0
		WHEN lower(GEODPMF) LIKE 'heartland/pr. n' THEN 4
		WHEN lower(GEODPMF) LIKE 'central vanc. is' THEN 3
		WHEN lower(GEODPMF) LIKE 'waterloo h.u.' THEN 7
		WHEN lower(GEODPMF) LIKE 'prairie m. h.' THEN 8
		WHEN lower(GEODPMF) LIKE 'capital district' THEN 5
		WHEN lower(GEODPMF) LIKE 'kingston-front.' THEN 3
		WHEN lower(GEODPMF) LIKE 'city of toro. h.' THEN 10
		WHEN lower(GEODPMF) LIKE 'north vanc. isl.' THEN 0
		WHEN lower(GEODPMF) LIKE 'peel region h' THEN 9
		WHEN lower(GEODPMF) LIKE 'western/labr' THEN 6
		WHEN lower(GEODPMF) LIKE 'cape breton' THEN 1
		WHEN lower(GEODPMF) LIKE 'calgary zone' THEN 9
		WHEN lower(GEODPMF) LIKE 'halton reg. h. u' THEN 6
		WHEN lower(GEODPMF) LIKE 'niagara reg. h.' THEN 7
		WHEN lower(GEODPMF) LIKE 'leeds-grenville' THEN 3
		WHEN lower(GEODPMF) LIKE 'north zone' THEN 9
		WHEN lower(GEODPMF) LIKE 'central regio ha' THEN 2
		WHEN lower(GEODPMF) LIKE 'zones 6 and 7' THEN 4
		WHEN lower(GEODPMF) LIKE 'northen rha' THEN 1
		WHEN lower(GEODPMF) LIKE 'northern inter.' THEN 2
		WHEN lower(GEODPMF) LIKE 'lambton h. unit' THEN 2
		WHEN lower(GEODPMF) LIKE 'east kootenay' THEN 0
		WHEN lower(GEODPMF) LIKE 'rég. abitibi' THEN 4
		WHEN lower(GEODPMF) LIKE 'saskatoon' THEN 5
		WHEN lower(GEODPMF) LIKE 'huron, perth un.' THEN 5
		WHEN lower(GEODPMF) LIKE 'thunder bay d. h' THEN 6
		WHEN lower(GEODPMF) LIKE 'prince albert/ma' THEN 3
		WHEN lower(GEODPMF) LIKE 'north bay h.u.' THEN 5
		WHEN lower(GEODPMF) LIKE 'northwestern h.' THEN 1
		WHEN lower(GEODPMF) LIKE 'fraser north' THEN 7
		WHEN lower(GEODPMF) LIKE 'simcoe musk. h' THEN 9
		WHEN lower(GEODPMF) LIKE 'zone 2' THEN 2
		WHEN lower(GEODPMF) LIKE 'sun country/five' THEN 8
		WHEN lower(GEODPMF) LIKE 'zone 3' THEN 2
		WHEN lower(GEODPMF) LIKE 'brant count' THEN 1
		WHEN lower(GEODPMF) LIKE 'zone 1' THEN 3
		WHEN lower(GEODPMF) LIKE 'okanagan' THEN 5
		WHEN lower(GEODPMF) LIKE 'porcupine h.' THEN 1
		WHEN lower(GEODPMF) LIKE 'elgin-st thomas' THEN 0
		WHEN lower(GEODPMF) LIKE 'north shore/c. g' THEN 4
		WHEN lower(GEODPMF) LIKE 'rég. des lauren.' THEN 7
		WHEN lower(GEODPMF) LIKE 'hastings. pie h.' THEN 2
		WHEN lower(GEODPMF) LIKE 'middlesex-lon. h' THEN 7
		WHEN lower(GEODPMF) LIKE 'peterb. county h' THEN 2
		WHEN lower(GEODPMF) LIKE 'south vanc. isl' THEN 6
		WHEN lower(GEODPMF) LIKE 'rég. la chaud.' THEN 6
		WHEN lower(GEODPMF) LIKE 'windsor-essex h' THEN 6
		WHEN lower(GEODPMF) LIKE 'city of ottawa h' THEN 8
		WHEN lower(GEODPMF) LIKE 'zones 4 and 5' THEN 3
		WHEN lower(GEODPMF) LIKE 'winnipeg rha' THEN 8
		WHEN lower(GEODPMF) LIKE 'oxford county h.' THEN 1
		WHEN lower(GEODPMF) LIKE 'dist. algoma' THEN 1
		WHEN lower(GEODPMF) LIKE 'interlake e. rha' THEN 4
		WHEN lower(GEODPMF) LIKE 'eastern regio ha' THEN 7
		WHEN lower(GEODPMF) LIKE 'northeast' THEN 0
		WHEN lower(GEODPMF) LIKE 'fraser east' THEN 3
		WHEN lower(GEODPMF) LIKE 'rég. cap. nat.' THEN 8
		WHEN lower(GEODPMF) LIKE 'thompson/carib.' THEN 3
		WHEN lower(GEODPMF) LIKE 'regina qu''appe' THEN 4
		WHEN lower(GEODPMF) LIKE 'rég. cote-nord' THEN 5
		WHEN lower(GEODPMF) LIKE 'don''t know' THEN null 
		WHEN lower(GEODPMF) LIKE 'n/s' THEN null 
		WHEN lower(GEODPMF) LIKE 'n/a' THEN null 
		WHEN lower(GEODPMF) LIKE 'refusal' THEN null 
		WHEN lower(GEODPMF) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEODPMF) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEODPMF) LIKE 'not required' THEN null 
	END AS GEODPMF,
	CASE
		WHEN lower(CIH_4) LIKE 'other' THEN 5
		WHEN lower(CIH_4) LIKE 'eating habits' THEN 8
		WHEN lower(CIH_4) LIKE 'more exercise' THEN 10
		WHEN lower(CIH_4) LIKE 'smoke less/stop' THEN 6
		WHEN lower(CIH_4) LIKE 'lost weight' THEN 7
		WHEN lower(CIH_4) LIKE 'medical treat' THEN 3
		WHEN lower(CIH_4) LIKE 'less alcohol' THEN 1
		WHEN lower(CIH_4) LIKE 'took vitamins' THEN 0
		WHEN lower(CIH_4) LIKE 'reduce stress le' THEN 2
		WHEN lower(CIH_4) LIKE 'don''t know' THEN null 
		WHEN lower(CIH_4) LIKE 'n/s' THEN null 
		WHEN lower(CIH_4) LIKE 'n/a' THEN null 
		WHEN lower(CIH_4) LIKE 'refusal' THEN null 
		WHEN lower(CIH_4) LIKE 'no drinks last w' THEN null 
		WHEN lower(CIH_4) LIKE 'no phy. activity' THEN null 
		WHEN lower(CIH_4) LIKE 'not required' THEN null 
	END AS CIH_4,
	CASE
		WHEN lower(PACDEE) LIKE '8.6' THEN 2
		WHEN lower(PACDEE) LIKE '8.7' THEN 2
		WHEN lower(PACDEE) LIKE '8.8' THEN 2
		WHEN lower(PACDEE) LIKE '8.9' THEN 2
		WHEN lower(PACDEE) LIKE '22.5' THEN 6
		WHEN lower(PACDEE) LIKE '22.4' THEN 6
		WHEN lower(PACDEE) LIKE '22.3' THEN 6
		WHEN lower(PACDEE) LIKE '22.2' THEN 6
		WHEN lower(PACDEE) LIKE '22.9' THEN 6
		WHEN lower(PACDEE) LIKE '22.7' THEN 6
		WHEN lower(PACDEE) LIKE '22.6' THEN 6
		WHEN lower(PACDEE) LIKE '10' THEN 2
		WHEN lower(PACDEE) LIKE '11' THEN 3
		WHEN lower(PACDEE) LIKE '12' THEN 3
		WHEN lower(PACDEE) LIKE '13' THEN 3
		WHEN lower(PACDEE) LIKE '14' THEN 3
		WHEN lower(PACDEE) LIKE '15' THEN 4
		WHEN lower(PACDEE) LIKE '18.9' THEN 5
		WHEN lower(PACDEE) LIKE '16' THEN 4
		WHEN lower(PACDEE) LIKE '18.8' THEN 5
		WHEN lower(PACDEE) LIKE '17' THEN 4
		WHEN lower(PACDEE) LIKE '18.7' THEN 5
		WHEN lower(PACDEE) LIKE '18' THEN 4
		WHEN lower(PACDEE) LIKE '10.6' THEN 2
		WHEN lower(PACDEE) LIKE '0' THEN 0
		WHEN lower(PACDEE) LIKE '10.5' THEN 2
		WHEN lower(PACDEE) LIKE '1' THEN 0
		WHEN lower(PACDEE) LIKE '10.4' THEN 2
		WHEN lower(PACDEE) LIKE '2' THEN 0
		WHEN lower(PACDEE) LIKE '10.3' THEN 2
		WHEN lower(PACDEE) LIKE '3' THEN 0
		WHEN lower(PACDEE) LIKE '10.2' THEN 2
		WHEN lower(PACDEE) LIKE '4' THEN 1
		WHEN lower(PACDEE) LIKE '10.1' THEN 2
		WHEN lower(PACDEE) LIKE '5' THEN 1
		WHEN lower(PACDEE) LIKE '6' THEN 1
		WHEN lower(PACDEE) LIKE '7' THEN 1
		WHEN lower(PACDEE) LIKE '18.6' THEN 5
		WHEN lower(PACDEE) LIKE '8' THEN 2
		WHEN lower(PACDEE) LIKE '18.5' THEN 5
		WHEN lower(PACDEE) LIKE '9' THEN 2
		WHEN lower(PACDEE) LIKE '18.4' THEN 5
		WHEN lower(PACDEE) LIKE '7.1' THEN 1
		WHEN lower(PACDEE) LIKE '18.3' THEN 5
		WHEN lower(PACDEE) LIKE '7.2' THEN 1
		WHEN lower(PACDEE) LIKE '18.2' THEN 4
		WHEN lower(PACDEE) LIKE '7.3' THEN 2
		WHEN lower(PACDEE) LIKE '18.1' THEN 4
		WHEN lower(PACDEE) LIKE '10.9' THEN 2
		WHEN lower(PACDEE) LIKE '7.4' THEN 2
		WHEN lower(PACDEE) LIKE '20' THEN 5
		WHEN lower(PACDEE) LIKE '10.8' THEN 2
		WHEN lower(PACDEE) LIKE '7.5' THEN 2
		WHEN lower(PACDEE) LIKE '21' THEN 5
		WHEN lower(PACDEE) LIKE '10.7' THEN 2
		WHEN lower(PACDEE) LIKE '7.6' THEN 2
		WHEN lower(PACDEE) LIKE '22' THEN 6
		WHEN lower(PACDEE) LIKE '7.7' THEN 2
		WHEN lower(PACDEE) LIKE '23' THEN 6
		WHEN lower(PACDEE) LIKE '7.8' THEN 2
		WHEN lower(PACDEE) LIKE '24' THEN 6
		WHEN lower(PACDEE) LIKE '7.9' THEN 2
		WHEN lower(PACDEE) LIKE '25' THEN 6
		WHEN lower(PACDEE) LIKE '26' THEN 7
		WHEN lower(PACDEE) LIKE '29.9' THEN 8
		WHEN lower(PACDEE) LIKE '29.7' THEN 8
		WHEN lower(PACDEE) LIKE '29' THEN 7
		WHEN lower(PACDEE) LIKE '21.6' THEN 5
		WHEN lower(PACDEE) LIKE '21.5' THEN 5
		WHEN lower(PACDEE) LIKE '21.4' THEN 5
		WHEN lower(PACDEE) LIKE '21.3' THEN 5
		WHEN lower(PACDEE) LIKE '21.2' THEN 5
		WHEN lower(PACDEE) LIKE '21.1' THEN 5
		WHEN lower(PACDEE) LIKE '29.3' THEN 8
		WHEN lower(PACDEE) LIKE '29.2' THEN 8
		WHEN lower(PACDEE) LIKE '30' THEN 8
		WHEN lower(PACDEE) LIKE '29.1' THEN 7
		WHEN lower(PACDEE) LIKE '21.8' THEN 5
		WHEN lower(PACDEE) LIKE '21.7' THEN 5
		WHEN lower(PACDEE) LIKE '17.9' THEN 4
		WHEN lower(PACDEE) LIKE '17.8' THEN 4
		WHEN lower(PACDEE) LIKE '17.7' THEN 4
		WHEN lower(PACDEE) LIKE '17.6' THEN 4
		WHEN lower(PACDEE) LIKE '6.1' THEN 1
		WHEN lower(PACDEE) LIKE '17.5' THEN 4
		WHEN lower(PACDEE) LIKE '6.2' THEN 1
		WHEN lower(PACDEE) LIKE '17.4' THEN 4
		WHEN lower(PACDEE) LIKE '6.3' THEN 1
		WHEN lower(PACDEE) LIKE '17.3' THEN 4
		WHEN lower(PACDEE) LIKE '6.4' THEN 1
		WHEN lower(PACDEE) LIKE '17.2' THEN 4
		WHEN lower(PACDEE) LIKE '6.5' THEN 1
		WHEN lower(PACDEE) LIKE '17.1' THEN 4
		WHEN lower(PACDEE) LIKE '6.6' THEN 1
		WHEN lower(PACDEE) LIKE '6.7' THEN 1
		WHEN lower(PACDEE) LIKE '6.8' THEN 1
		WHEN lower(PACDEE) LIKE '6.9' THEN 1
		WHEN lower(PACDEE) LIKE '20.7' THEN 5
		WHEN lower(PACDEE) LIKE '20.6' THEN 5
		WHEN lower(PACDEE) LIKE '20.5' THEN 5
		WHEN lower(PACDEE) LIKE '20.4' THEN 5
		WHEN lower(PACDEE) LIKE '20.3' THEN 5
		WHEN lower(PACDEE) LIKE '20.2' THEN 5
		WHEN lower(PACDEE) LIKE '20.1' THEN 5
		WHEN lower(PACDEE) LIKE '28.7' THEN 7
		WHEN lower(PACDEE) LIKE '28.6' THEN 7
		WHEN lower(PACDEE) LIKE '28.4' THEN 7
		WHEN lower(PACDEE) LIKE '20.9' THEN 5
		WHEN lower(PACDEE) LIKE '20.8' THEN 5
		WHEN lower(PACDEE) LIKE '16.9' THEN 4
		WHEN lower(PACDEE) LIKE '16.8' THEN 4
		WHEN lower(PACDEE) LIKE '5.1' THEN 1
		WHEN lower(PACDEE) LIKE '16.7' THEN 4
		WHEN lower(PACDEE) LIKE '5.2' THEN 1
		WHEN lower(PACDEE) LIKE '16.6' THEN 4
		WHEN lower(PACDEE) LIKE '5.3' THEN 1
		WHEN lower(PACDEE) LIKE '16.5' THEN 4
		WHEN lower(PACDEE) LIKE '5.4' THEN 1
		WHEN lower(PACDEE) LIKE '16.4' THEN 4
		WHEN lower(PACDEE) LIKE '5.5' THEN 1
		WHEN lower(PACDEE) LIKE '16.3' THEN 4
		WHEN lower(PACDEE) LIKE '5.6' THEN 1
		WHEN lower(PACDEE) LIKE '16.2' THEN 4
		WHEN lower(PACDEE) LIKE '5.7' THEN 1
		WHEN lower(PACDEE) LIKE '16.1' THEN 4
		WHEN lower(PACDEE) LIKE '5.8' THEN 1
		WHEN lower(PACDEE) LIKE '5.9' THEN 1
		WHEN lower(PACDEE) LIKE '27.7' THEN 7
		WHEN lower(PACDEE) LIKE '27.2' THEN 7
		WHEN lower(PACDEE) LIKE '27.1' THEN 7
		WHEN lower(PACDEE) LIKE '15.1' THEN 4
		WHEN lower(PACDEE) LIKE '30.1' THEN 8
		WHEN lower(PACDEE) LIKE '4.1' THEN 1
		WHEN lower(PACDEE) LIKE '15.9' THEN 4
		WHEN lower(PACDEE) LIKE '4.2' THEN 1
		WHEN lower(PACDEE) LIKE '15.8' THEN 4
		WHEN lower(PACDEE) LIKE '4.3' THEN 1
		WHEN lower(PACDEE) LIKE '15.7' THEN 4
		WHEN lower(PACDEE) LIKE '4.4' THEN 1
		WHEN lower(PACDEE) LIKE '15.6' THEN 4
		WHEN lower(PACDEE) LIKE '4.5' THEN 1
		WHEN lower(PACDEE) LIKE '15.5' THEN 4
		WHEN lower(PACDEE) LIKE '4.6' THEN 1
		WHEN lower(PACDEE) LIKE '15.4' THEN 4
		WHEN lower(PACDEE) LIKE '4.7' THEN 1
		WHEN lower(PACDEE) LIKE '15.3' THEN 4
		WHEN lower(PACDEE) LIKE '4.8' THEN 1
		WHEN lower(PACDEE) LIKE '30.9' THEN 8
		WHEN lower(PACDEE) LIKE '15.2' THEN 4
		WHEN lower(PACDEE) LIKE '4.9' THEN 1
		WHEN lower(PACDEE) LIKE '26.1' THEN 7
		WHEN lower(PACDEE) LIKE '26.9' THEN 7
		WHEN lower(PACDEE) LIKE '26.5' THEN 7
		WHEN lower(PACDEE) LIKE '26.3' THEN 7
		WHEN lower(PACDEE) LIKE '26.2' THEN 7
		WHEN lower(PACDEE) LIKE '14.2' THEN 3
		WHEN lower(PACDEE) LIKE '14.1' THEN 3
		WHEN lower(PACDEE) LIKE '3.1' THEN 0
		WHEN lower(PACDEE) LIKE '3.2' THEN 0
		WHEN lower(PACDEE) LIKE '3.3' THEN 0
		WHEN lower(PACDEE) LIKE '14.9' THEN 4
		WHEN lower(PACDEE) LIKE '3.4' THEN 0
		WHEN lower(PACDEE) LIKE '14.8' THEN 4
		WHEN lower(PACDEE) LIKE '3.5' THEN 0
		WHEN lower(PACDEE) LIKE '14.7' THEN 4
		WHEN lower(PACDEE) LIKE '3.6' THEN 0
		WHEN lower(PACDEE) LIKE '14.6' THEN 4
		WHEN lower(PACDEE) LIKE '3.7' THEN 1
		WHEN lower(PACDEE) LIKE '14.5' THEN 3
		WHEN lower(PACDEE) LIKE '3.8' THEN 1
		WHEN lower(PACDEE) LIKE '14.4' THEN 3
		WHEN lower(PACDEE) LIKE '3.9' THEN 1
		WHEN lower(PACDEE) LIKE '14.3' THEN 3
		WHEN lower(PACDEE) LIKE '25.2' THEN 6
		WHEN lower(PACDEE) LIKE '25.6' THEN 7
		WHEN lower(PACDEE) LIKE '25.5' THEN 6
		WHEN lower(PACDEE) LIKE '25.3' THEN 6
		WHEN lower(PACDEE) LIKE '13.3' THEN 3
		WHEN lower(PACDEE) LIKE '13.2' THEN 3
		WHEN lower(PACDEE) LIKE '13.1' THEN 3
		WHEN lower(PACDEE) LIKE '2.1' THEN 0
		WHEN lower(PACDEE) LIKE '2.2' THEN 0
		WHEN lower(PACDEE) LIKE '2.3' THEN 0
		WHEN lower(PACDEE) LIKE '2.4' THEN 0
		WHEN lower(PACDEE) LIKE '2.5' THEN 0
		WHEN lower(PACDEE) LIKE '13.9' THEN 3
		WHEN lower(PACDEE) LIKE '2.6' THEN 0
		WHEN lower(PACDEE) LIKE '13.8' THEN 3
		WHEN lower(PACDEE) LIKE '2.7' THEN 0
		WHEN lower(PACDEE) LIKE '13.7' THEN 3
		WHEN lower(PACDEE) LIKE '2.8' THEN 0
		WHEN lower(PACDEE) LIKE '36.5' THEN 10
		WHEN lower(PACDEE) LIKE '13.6' THEN 3
		WHEN lower(PACDEE) LIKE '2.9' THEN 0
		WHEN lower(PACDEE) LIKE '13.5' THEN 3
		WHEN lower(PACDEE) LIKE '13.4' THEN 3
		WHEN lower(PACDEE) LIKE '24.3' THEN 6
		WHEN lower(PACDEE) LIKE '24.2' THEN 6
		WHEN lower(PACDEE) LIKE '24.1' THEN 6
		WHEN lower(PACDEE) LIKE '24.9' THEN 6
		WHEN lower(PACDEE) LIKE '24.7' THEN 6
		WHEN lower(PACDEE) LIKE '12.4' THEN 3
		WHEN lower(PACDEE) LIKE '12.3' THEN 3
		WHEN lower(PACDEE) LIKE '12.2' THEN 3
		WHEN lower(PACDEE) LIKE '12.1' THEN 3
		WHEN lower(PACDEE) LIKE '1.1' THEN 0
		WHEN lower(PACDEE) LIKE '1.2' THEN 0
		WHEN lower(PACDEE) LIKE '1.3' THEN 0
		WHEN lower(PACDEE) LIKE '1.4' THEN 0
		WHEN lower(PACDEE) LIKE '1.5' THEN 0
		WHEN lower(PACDEE) LIKE '1.6' THEN 0
		WHEN lower(PACDEE) LIKE '1.7' THEN 0
		WHEN lower(PACDEE) LIKE '12.9' THEN 3
		WHEN lower(PACDEE) LIKE '1.8' THEN 0
		WHEN lower(PACDEE) LIKE '35.7' THEN 9
		WHEN lower(PACDEE) LIKE '12.8' THEN 3
		WHEN lower(PACDEE) LIKE '9.1' THEN 2
		WHEN lower(PACDEE) LIKE '1.9' THEN 0
		WHEN lower(PACDEE) LIKE '12.7' THEN 3
		WHEN lower(PACDEE) LIKE '9.2' THEN 2
		WHEN lower(PACDEE) LIKE '12.6' THEN 3
		WHEN lower(PACDEE) LIKE '9.3' THEN 2
		WHEN lower(PACDEE) LIKE '12.5' THEN 3
		WHEN lower(PACDEE) LIKE '9.4' THEN 2
		WHEN lower(PACDEE) LIKE '9.5' THEN 2
		WHEN lower(PACDEE) LIKE '9.6' THEN 2
		WHEN lower(PACDEE) LIKE '9.7' THEN 2
		WHEN lower(PACDEE) LIKE '9.8' THEN 2
		WHEN lower(PACDEE) LIKE '9.9' THEN 2
		WHEN lower(PACDEE) LIKE '23.3' THEN 6
		WHEN lower(PACDEE) LIKE '23.2' THEN 6
		WHEN lower(PACDEE) LIKE '23.1' THEN 6
		WHEN lower(PACDEE) LIKE '23.9' THEN 6
		WHEN lower(PACDEE) LIKE '23.8' THEN 6
		WHEN lower(PACDEE) LIKE '23.7' THEN 6
		WHEN lower(PACDEE) LIKE '23.6' THEN 6
		WHEN lower(PACDEE) LIKE '19.9' THEN 5
		WHEN lower(PACDEE) LIKE '19.8' THEN 5
		WHEN lower(PACDEE) LIKE '19.7' THEN 5
		WHEN lower(PACDEE) LIKE '19.6' THEN 5
		WHEN lower(PACDEE) LIKE '11.5' THEN 3
		WHEN lower(PACDEE) LIKE '11.4' THEN 3
		WHEN lower(PACDEE) LIKE '11.3' THEN 3
		WHEN lower(PACDEE) LIKE '11.2' THEN 3
		WHEN lower(PACDEE) LIKE '0.1' THEN 0
		WHEN lower(PACDEE) LIKE '11.1' THEN 3
		WHEN lower(PACDEE) LIKE '0.2' THEN 0
		WHEN lower(PACDEE) LIKE '0.3' THEN 0
		WHEN lower(PACDEE) LIKE '0.4' THEN 0
		WHEN lower(PACDEE) LIKE '0.5' THEN 0
		WHEN lower(PACDEE) LIKE '19.5' THEN 5
		WHEN lower(PACDEE) LIKE '0.6' THEN 0
		WHEN lower(PACDEE) LIKE '19.4' THEN 5
		WHEN lower(PACDEE) LIKE '0.7' THEN 0
		WHEN lower(PACDEE) LIKE '19.3' THEN 5
		WHEN lower(PACDEE) LIKE '0.8' THEN 0
		WHEN lower(PACDEE) LIKE '19.2' THEN 5
		WHEN lower(PACDEE) LIKE '8.1' THEN 2
		WHEN lower(PACDEE) LIKE '0.9' THEN 0
		WHEN lower(PACDEE) LIKE '19.1' THEN 5
		WHEN lower(PACDEE) LIKE '11.9' THEN 3
		WHEN lower(PACDEE) LIKE '8.2' THEN 2
		WHEN lower(PACDEE) LIKE '11.8' THEN 3
		WHEN lower(PACDEE) LIKE '8.3' THEN 2
		WHEN lower(PACDEE) LIKE '11.7' THEN 3
		WHEN lower(PACDEE) LIKE '8.4' THEN 2
		WHEN lower(PACDEE) LIKE '11.6' THEN 3
		WHEN lower(PACDEE) LIKE '8.5' THEN 2
		WHEN lower(PACDEE) LIKE 'don''t know' THEN null 
		WHEN lower(PACDEE) LIKE 'n/s' THEN null 
		WHEN lower(PACDEE) LIKE 'n/a' THEN null 
		WHEN lower(PACDEE) LIKE 'refusal' THEN null 
		WHEN lower(PACDEE) LIKE 'no drinks last w' THEN null 
		WHEN lower(PACDEE) LIKE 'no phy. activity' THEN null 
		WHEN lower(PACDEE) LIKE 'not required' THEN null 
	END AS PACDEE,
	CASE
		WHEN lower(DHHGLVG) LIKE 'parent child' THEN 0
		WHEN lower(DHHGLVG) LIKE 'child parent sib' THEN 2
		WHEN lower(DHHGLVG) LIKE 'other' THEN 4
		WHEN lower(DHHGLVG) LIKE 'unattached oth' THEN 1
		WHEN lower(DHHGLVG) LIKE 'unattached alone' THEN 8
		WHEN lower(DHHGLVG) LIKE 'child 2 par sib' THEN 5
		WHEN lower(DHHGLVG) LIKE 'spouse / parter' THEN 10
		WHEN lower(DHHGLVG) LIKE 'par spouse child' THEN 7
		WHEN lower(DHHGLVG) LIKE 'don''t know' THEN null 
		WHEN lower(DHHGLVG) LIKE 'n/s' THEN null 
		WHEN lower(DHHGLVG) LIKE 'n/a' THEN null 
		WHEN lower(DHHGLVG) LIKE 'refusal' THEN null 
		WHEN lower(DHHGLVG) LIKE 'no drinks last w' THEN null 
		WHEN lower(DHHGLVG) LIKE 'no phy. activity' THEN null 
		WHEN lower(DHHGLVG) LIKE 'not required' THEN null 
	END AS DHHGLVG,
	CASE
		WHEN lower(CIH_1) LIKE 'no' THEN 0
		WHEN lower(CIH_1) LIKE 'yes' THEN 10
		WHEN lower(CIH_1) LIKE 'don''t know' THEN null 
		WHEN lower(CIH_1) LIKE 'n/s' THEN null 
		WHEN lower(CIH_1) LIKE 'n/a' THEN null 
		WHEN lower(CIH_1) LIKE 'refusal' THEN null 
		WHEN lower(CIH_1) LIKE 'no drinks last w' THEN null 
		WHEN lower(CIH_1) LIKE 'no phy. activity' THEN null 
		WHEN lower(CIH_1) LIKE 'not required' THEN null 
	END AS CIH_1,
	CASE
		WHEN lower(HUPDPAD) LIKE 'prevents most' THEN 0
		WHEN lower(HUPDPAD) LIKE 'prevents some' THEN 2
		WHEN lower(HUPDPAD) LIKE 'no pain' THEN 10
		WHEN lower(HUPDPAD) LIKE 'prevents a few' THEN 5
		WHEN lower(HUPDPAD) LIKE 'doesn''t prevent' THEN 7
		WHEN lower(HUPDPAD) LIKE 'don''t know' THEN null 
		WHEN lower(HUPDPAD) LIKE 'n/s' THEN null 
		WHEN lower(HUPDPAD) LIKE 'n/a' THEN null 
		WHEN lower(HUPDPAD) LIKE 'refusal' THEN null 
		WHEN lower(HUPDPAD) LIKE 'no drinks last w' THEN null 
		WHEN lower(HUPDPAD) LIKE 'no phy. activity' THEN null 
		WHEN lower(HUPDPAD) LIKE 'not required' THEN null 
	END AS HUPDPAD,
	CASE
		WHEN lower(CIH_2) LIKE 'other' THEN 5
		WHEN lower(CIH_2) LIKE 'eating habits' THEN 8
		WHEN lower(CIH_2) LIKE 'more exercise' THEN 10
		WHEN lower(CIH_2) LIKE 'smoke less/stop' THEN 6
		WHEN lower(CIH_2) LIKE 'lost weight' THEN 7
		WHEN lower(CIH_2) LIKE 'medical treat' THEN 3
		WHEN lower(CIH_2) LIKE 'less alcohol' THEN 1
		WHEN lower(CIH_2) LIKE 'took vitamins' THEN 0
		WHEN lower(CIH_2) LIKE 'reduce stress le' THEN 2
		WHEN lower(CIH_2) LIKE 'don''t know' THEN null 
		WHEN lower(CIH_2) LIKE 'n/s' THEN null 
		WHEN lower(CIH_2) LIKE 'n/a' THEN null 
		WHEN lower(CIH_2) LIKE 'refusal' THEN null 
		WHEN lower(CIH_2) LIKE 'no drinks last w' THEN null 
		WHEN lower(CIH_2) LIKE 'no phy. activity' THEN null 
		WHEN lower(CIH_2) LIKE 'not required' THEN null 
	END AS CIH_2,
	CASE
		WHEN lower(SPSDATT) LIKE '2' THEN 2
		WHEN lower(SPSDATT) LIKE '3' THEN 4
		WHEN lower(SPSDATT) LIKE '4' THEN 5
		WHEN lower(SPSDATT) LIKE '5' THEN 7
		WHEN lower(SPSDATT) LIKE '6' THEN 8
		WHEN lower(SPSDATT) LIKE '7' THEN 10
		WHEN lower(SPSDATT) LIKE '8' THEN 11
		WHEN lower(SPSDATT) LIKE '9' THEN 12
		WHEN lower(SPSDATT) LIKE 'don''t know' THEN null 
		WHEN lower(SPSDATT) LIKE 'n/s' THEN null 
		WHEN lower(SPSDATT) LIKE 'n/a' THEN null 
		WHEN lower(SPSDATT) LIKE 'refusal' THEN null 
		WHEN lower(SPSDATT) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPSDATT) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPSDATT) LIKE 'not required' THEN null 
	END AS SPSDATT,
	CASE
		WHEN lower(GEOGPRV) LIKE 'yukon/nwt/nuna.' THEN 1
		WHEN lower(GEOGPRV) LIKE 'pei' THEN 0
		WHEN lower(GEOGPRV) LIKE 'ontario' THEN 10
		WHEN lower(GEOGPRV) LIKE 'manitoba' THEN 5
		WHEN lower(GEOGPRV) LIKE 'saskatchewan' THEN 6
		WHEN lower(GEOGPRV) LIKE 'nova scotia' THEN 3
		WHEN lower(GEOGPRV) LIKE 'new brunswick' THEN 4
		WHEN lower(GEOGPRV) LIKE 'british columbia' THEN 8
		WHEN lower(GEOGPRV) LIKE 'nfld & lab.' THEN 2
		WHEN lower(GEOGPRV) LIKE 'quebec' THEN 9
		WHEN lower(GEOGPRV) LIKE 'alberta' THEN 7
		WHEN lower(GEOGPRV) LIKE 'don''t know' THEN null 
		WHEN lower(GEOGPRV) LIKE 'n/s' THEN null 
		WHEN lower(GEOGPRV) LIKE 'n/a' THEN null 
		WHEN lower(GEOGPRV) LIKE 'refusal' THEN null 
		WHEN lower(GEOGPRV) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEOGPRV) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEOGPRV) LIKE 'not required' THEN null 
	END AS GEOGPRV,
	CASE
		WHEN lower(CHPG04) LIKE '22' THEN 7
		WHEN lower(CHPG04) LIKE '23' THEN 7
		WHEN lower(CHPG04) LIKE '24' THEN 7
		WHEN lower(CHPG04) LIKE '25' THEN 8
		WHEN lower(CHPG04) LIKE '26' THEN 8
		WHEN lower(CHPG04) LIKE '27' THEN 8
		WHEN lower(CHPG04) LIKE '28' THEN 9
		WHEN lower(CHPG04) LIKE '29' THEN 9
		WHEN lower(CHPG04) LIKE '30' THEN 9
		WHEN lower(CHPG04) LIKE '31+ consultation' THEN 10
		WHEN lower(CHPG04) LIKE '10' THEN 3
		WHEN lower(CHPG04) LIKE '11' THEN 3
		WHEN lower(CHPG04) LIKE '12' THEN 3
		WHEN lower(CHPG04) LIKE '13' THEN 4
		WHEN lower(CHPG04) LIKE '14' THEN 4
		WHEN lower(CHPG04) LIKE '15' THEN 4
		WHEN lower(CHPG04) LIKE '16' THEN 5
		WHEN lower(CHPG04) LIKE '17' THEN 5
		WHEN lower(CHPG04) LIKE '18' THEN 5
		WHEN lower(CHPG04) LIKE '19' THEN 6
		WHEN lower(CHPG04) LIKE '0' THEN 0
		WHEN lower(CHPG04) LIKE '1' THEN 0
		WHEN lower(CHPG04) LIKE '2' THEN 0
		WHEN lower(CHPG04) LIKE '3' THEN 0
		WHEN lower(CHPG04) LIKE '4' THEN 1
		WHEN lower(CHPG04) LIKE '5' THEN 1
		WHEN lower(CHPG04) LIKE '6' THEN 1
		WHEN lower(CHPG04) LIKE '7' THEN 2
		WHEN lower(CHPG04) LIKE '8' THEN 2
		WHEN lower(CHPG04) LIKE '9' THEN 2
		WHEN lower(CHPG04) LIKE '20' THEN 6
		WHEN lower(CHPG04) LIKE '21' THEN 6
		WHEN lower(CHPG04) LIKE 'don''t know' THEN null 
		WHEN lower(CHPG04) LIKE 'n/s' THEN null 
		WHEN lower(CHPG04) LIKE 'n/a' THEN null 
		WHEN lower(CHPG04) LIKE 'refusal' THEN null 
		WHEN lower(CHPG04) LIKE 'no drinks last w' THEN null 
		WHEN lower(CHPG04) LIKE 'no phy. activity' THEN null 
		WHEN lower(CHPG04) LIKE 'not required' THEN null 
	END AS CHPG04,
	CASE
		WHEN lower(SPSDWOR) LIKE '2' THEN 3
		WHEN lower(SPSDWOR) LIKE '3' THEN 5
		WHEN lower(SPSDWOR) LIKE '4' THEN 6
		WHEN lower(SPSDWOR) LIKE '5' THEN 8
		WHEN lower(SPSDWOR) LIKE '6' THEN 10
		WHEN lower(SPSDWOR) LIKE '7' THEN 11
		WHEN lower(SPSDWOR) LIKE '8' THEN 13
		WHEN lower(SPSDWOR) LIKE 'don''t know' THEN null 
		WHEN lower(SPSDWOR) LIKE 'n/s' THEN null 
		WHEN lower(SPSDWOR) LIKE 'n/a' THEN null 
		WHEN lower(SPSDWOR) LIKE 'refusal' THEN null 
		WHEN lower(SPSDWOR) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPSDWOR) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPSDWOR) LIKE 'not required' THEN null 
	END AS SPSDWOR,
	CASE
		WHEN lower(GEN_10) LIKE 'very strong' THEN 13
		WHEN lower(GEN_10) LIKE 'somewhat strong' THEN 10
		WHEN lower(GEN_10) LIKE 'very weak' THEN 6
		WHEN lower(GEN_10) LIKE 'somewhat weak' THEN 3
		WHEN lower(GEN_10) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_10) LIKE 'n/s' THEN null 
		WHEN lower(GEN_10) LIKE 'n/a' THEN null 
		WHEN lower(GEN_10) LIKE 'refusal' THEN null 
		WHEN lower(GEN_10) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_10) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_10) LIKE 'not required' THEN null 
	END AS GEN_10,
	CASE
		WHEN lower(CHPGMDC) LIKE '22' THEN 7
		WHEN lower(CHPGMDC) LIKE '23' THEN 7
		WHEN lower(CHPGMDC) LIKE '24' THEN 7
		WHEN lower(CHPGMDC) LIKE '25' THEN 8
		WHEN lower(CHPGMDC) LIKE '26' THEN 8
		WHEN lower(CHPGMDC) LIKE '27' THEN 8
		WHEN lower(CHPGMDC) LIKE '28' THEN 9
		WHEN lower(CHPGMDC) LIKE '29' THEN 9
		WHEN lower(CHPGMDC) LIKE '30' THEN 9
		WHEN lower(CHPGMDC) LIKE '31+ consultation' THEN 10
		WHEN lower(CHPGMDC) LIKE '10' THEN 3
		WHEN lower(CHPGMDC) LIKE '11' THEN 3
		WHEN lower(CHPGMDC) LIKE '12' THEN 3
		WHEN lower(CHPGMDC) LIKE '13' THEN 4
		WHEN lower(CHPGMDC) LIKE '14' THEN 4
		WHEN lower(CHPGMDC) LIKE '15' THEN 4
		WHEN lower(CHPGMDC) LIKE '16' THEN 5
		WHEN lower(CHPGMDC) LIKE '17' THEN 5
		WHEN lower(CHPGMDC) LIKE '18' THEN 5
		WHEN lower(CHPGMDC) LIKE '19' THEN 6
		WHEN lower(CHPGMDC) LIKE '0' THEN 0
		WHEN lower(CHPGMDC) LIKE '1' THEN 0
		WHEN lower(CHPGMDC) LIKE '2' THEN 0
		WHEN lower(CHPGMDC) LIKE '3' THEN 0
		WHEN lower(CHPGMDC) LIKE '4' THEN 1
		WHEN lower(CHPGMDC) LIKE '5' THEN 1
		WHEN lower(CHPGMDC) LIKE '6' THEN 1
		WHEN lower(CHPGMDC) LIKE '7' THEN 2
		WHEN lower(CHPGMDC) LIKE '8' THEN 2
		WHEN lower(CHPGMDC) LIKE '9' THEN 2
		WHEN lower(CHPGMDC) LIKE '20' THEN 6
		WHEN lower(CHPGMDC) LIKE '21' THEN 6
		WHEN lower(CHPGMDC) LIKE 'don''t know' THEN null 
		WHEN lower(CHPGMDC) LIKE 'n/s' THEN null 
		WHEN lower(CHPGMDC) LIKE 'n/a' THEN null 
		WHEN lower(CHPGMDC) LIKE 'refusal' THEN null 
		WHEN lower(CHPGMDC) LIKE 'no drinks last w' THEN null 
		WHEN lower(CHPGMDC) LIKE 'no phy. activity' THEN null 
		WHEN lower(CHPGMDC) LIKE 'not required' THEN null 
	END AS CHPGMDC,
	CASE
		WHEN lower(SFE_504) LIKE 'strong disagree' THEN 0
		WHEN lower(SFE_504) LIKE 'strongly agree' THEN 10
		WHEN lower(SFE_504) LIKE 'neither nor' THEN 5
		WHEN lower(SFE_504) LIKE 'disagree' THEN 2
		WHEN lower(SFE_504) LIKE 'agree' THEN 7
		WHEN lower(SFE_504) LIKE 'don''t know' THEN null 
		WHEN lower(SFE_504) LIKE 'n/s' THEN null 
		WHEN lower(SFE_504) LIKE 'n/a' THEN null 
		WHEN lower(SFE_504) LIKE 'refusal' THEN null 
		WHEN lower(SFE_504) LIKE 'no drinks last w' THEN null 
		WHEN lower(SFE_504) LIKE 'no phy. activity' THEN null 
		WHEN lower(SFE_504) LIKE 'not required' THEN null 
	END AS SFE_504,
	CASE
		WHEN lower(DISDCHR) LIKE 'little less' THEN 2
		WHEN lower(DISDCHR) LIKE 'never had any' THEN 0
		WHEN lower(DISDCHR) LIKE 'somewhat more' THEN 8
		WHEN lower(DISDCHR) LIKE 'little more' THEN 7
		WHEN lower(DISDCHR) LIKE 'somewhat less' THEN 4
		WHEN lower(DISDCHR) LIKE 'a lot less' THEN 1
		WHEN lower(DISDCHR) LIKE 'about the same' THEN 5
		WHEN lower(DISDCHR) LIKE 'a lot more' THEN 10
		WHEN lower(DISDCHR) LIKE 'don''t know' THEN null 
		WHEN lower(DISDCHR) LIKE 'n/s' THEN null 
		WHEN lower(DISDCHR) LIKE 'n/a' THEN null 
		WHEN lower(DISDCHR) LIKE 'refusal' THEN null 
		WHEN lower(DISDCHR) LIKE 'no drinks last w' THEN null 
		WHEN lower(DISDCHR) LIKE 'no phy. activity' THEN null 
		WHEN lower(DISDCHR) LIKE 'not required' THEN null 
	END AS DISDCHR,
	CASE
		WHEN lower(DHHGAGE) LIKE '18 to 19 years' THEN 2
		WHEN lower(DHHGAGE) LIKE '75 to 79 years' THEN 11
		WHEN lower(DHHGAGE) LIKE '55 to 59 years' THEN 8
		WHEN lower(DHHGAGE) LIKE '65 to 69 years' THEN 9
		WHEN lower(DHHGAGE) LIKE '12 to 14 years' THEN 1
		WHEN lower(DHHGAGE) LIKE '20 to 24 years' THEN 2
		WHEN lower(DHHGAGE) LIKE '15 to 17 years' THEN 2
		WHEN lower(DHHGAGE) LIKE '30 to 34 years' THEN 4
		WHEN lower(DHHGAGE) LIKE '50 to 54 years' THEN 7
		WHEN lower(DHHGAGE) LIKE '40 to 44 years' THEN 5
		WHEN lower(DHHGAGE) LIKE '70 to 74 years' THEN 10
		WHEN lower(DHHGAGE) LIKE '25 to 29 years' THEN 3
		WHEN lower(DHHGAGE) LIKE '80 years or more' THEN 11
		WHEN lower(DHHGAGE) LIKE '45 to 49 years' THEN 6
		WHEN lower(DHHGAGE) LIKE '35 to 39 years' THEN 5
		WHEN lower(DHHGAGE) LIKE '60 to 64 years' THEN 8
		WHEN lower(DHHGAGE) LIKE 'don''t know' THEN null 
		WHEN lower(DHHGAGE) LIKE 'n/s' THEN null 
		WHEN lower(DHHGAGE) LIKE 'n/a' THEN null 
		WHEN lower(DHHGAGE) LIKE 'refusal' THEN null 
		WHEN lower(DHHGAGE) LIKE 'no drinks last w' THEN null 
		WHEN lower(DHHGAGE) LIKE 'no phy. activity' THEN null 
		WHEN lower(DHHGAGE) LIKE 'not required' THEN null 
	END AS DHHGAGE,
	CASE
		WHEN lower(GEN_09) LIKE 'quite a bit' THEN 7
		WHEN lower(GEN_09) LIKE 'not at all' THEN 0
		WHEN lower(GEN_09) LIKE 'not very' THEN 5
		WHEN lower(GEN_09) LIKE 'extremely' THEN 10
		WHEN lower(GEN_09) LIKE 'a bit' THEN 2
		WHEN lower(GEN_09) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_09) LIKE 'n/s' THEN null 
		WHEN lower(GEN_09) LIKE 'n/a' THEN null 
		WHEN lower(GEN_09) LIKE 'refusal' THEN null 
		WHEN lower(GEN_09) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_09) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_09) LIKE 'not required' THEN null 
	END AS GEN_09,
	CASE
		WHEN lower(ACC_40) LIKE 'no' THEN 0
		WHEN lower(ACC_40) LIKE 'yes' THEN 10
		WHEN lower(ACC_40) LIKE 'don''t know' THEN null 
		WHEN lower(ACC_40) LIKE 'n/s' THEN null 
		WHEN lower(ACC_40) LIKE 'n/a' THEN null 
		WHEN lower(ACC_40) LIKE 'refusal' THEN null 
		WHEN lower(ACC_40) LIKE 'no drinks last w' THEN null 
		WHEN lower(ACC_40) LIKE 'no phy. activity' THEN null 
		WHEN lower(ACC_40) LIKE 'not required' THEN null 
	END AS ACC_40,
	CASE
		WHEN lower(SFEDE1) LIKE '22' THEN 6
		WHEN lower(SFEDE1) LIKE '23' THEN 7
		WHEN lower(SFEDE1) LIKE '24' THEN 7
		WHEN lower(SFEDE1) LIKE '25' THEN 7
		WHEN lower(SFEDE1) LIKE '26' THEN 8
		WHEN lower(SFEDE1) LIKE '27' THEN 8
		WHEN lower(SFEDE1) LIKE '28' THEN 8
		WHEN lower(SFEDE1) LIKE '29' THEN 9
		WHEN lower(SFEDE1) LIKE '30' THEN 9
		WHEN lower(SFEDE1) LIKE '31' THEN 9
		WHEN lower(SFEDE1) LIKE '10' THEN 3
		WHEN lower(SFEDE1) LIKE '32' THEN 10
		WHEN lower(SFEDE1) LIKE '11' THEN 3
		WHEN lower(SFEDE1) LIKE '12' THEN 3
		WHEN lower(SFEDE1) LIKE '13' THEN 4
		WHEN lower(SFEDE1) LIKE '14' THEN 4
		WHEN lower(SFEDE1) LIKE '15' THEN 4
		WHEN lower(SFEDE1) LIKE '16' THEN 5
		WHEN lower(SFEDE1) LIKE '17' THEN 5
		WHEN lower(SFEDE1) LIKE '18' THEN 5
		WHEN lower(SFEDE1) LIKE '19' THEN 5
		WHEN lower(SFEDE1) LIKE '0' THEN 0
		WHEN lower(SFEDE1) LIKE '1' THEN 0
		WHEN lower(SFEDE1) LIKE '2' THEN 0
		WHEN lower(SFEDE1) LIKE '3' THEN 0
		WHEN lower(SFEDE1) LIKE '4' THEN 1
		WHEN lower(SFEDE1) LIKE '5' THEN 1
		WHEN lower(SFEDE1) LIKE '6' THEN 1
		WHEN lower(SFEDE1) LIKE '7' THEN 2
		WHEN lower(SFEDE1) LIKE '8' THEN 2
		WHEN lower(SFEDE1) LIKE '9' THEN 2
		WHEN lower(SFEDE1) LIKE '20' THEN 6
		WHEN lower(SFEDE1) LIKE '21' THEN 6
		WHEN lower(SFEDE1) LIKE 'don''t know' THEN null 
		WHEN lower(SFEDE1) LIKE 'n/s' THEN null 
		WHEN lower(SFEDE1) LIKE 'n/a' THEN null 
		WHEN lower(SFEDE1) LIKE 'refusal' THEN null 
		WHEN lower(SFEDE1) LIKE 'no drinks last w' THEN null 
		WHEN lower(SFEDE1) LIKE 'no phy. activity' THEN null 
		WHEN lower(SFEDE1) LIKE 'not required' THEN null 
	END AS SFEDE1,
	CASE
		WHEN lower(SPV_6) LIKE 'not at all' THEN 10
		WHEN lower(SPV_6) LIKE 'once/month' THEN 2
		WHEN lower(SPV_6) LIKE 'once/year' THEN 5
		WHEN lower(SPV_6) LIKE 'once/week or +' THEN 7
		WHEN lower(SPV_6) LIKE '3 to 4 times/yr' THEN 0
		WHEN lower(SPV_6) LIKE 'don''t know' THEN null 
		WHEN lower(SPV_6) LIKE 'n/s' THEN null 
		WHEN lower(SPV_6) LIKE 'n/a' THEN null 
		WHEN lower(SPV_6) LIKE 'refusal' THEN null 
		WHEN lower(SPV_6) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPV_6) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPV_6) LIKE 'not required' THEN null 
	END AS SPV_6,
	CASE
		WHEN lower(FVCDTOT) LIKE '8.6' THEN 2
		WHEN lower(FVCDTOT) LIKE '8.7' THEN 2
		WHEN lower(FVCDTOT) LIKE '8.8' THEN 2
		WHEN lower(FVCDTOT) LIKE '8.9' THEN 2
		WHEN lower(FVCDTOT) LIKE '22.5' THEN 6
		WHEN lower(FVCDTOT) LIKE '22.4' THEN 6
		WHEN lower(FVCDTOT) LIKE '22.3' THEN 6
		WHEN lower(FVCDTOT) LIKE '22.2' THEN 6
		WHEN lower(FVCDTOT) LIKE '22.1' THEN 6
		WHEN lower(FVCDTOT) LIKE '22.6' THEN 6
		WHEN lower(FVCDTOT) LIKE '10' THEN 2
		WHEN lower(FVCDTOT) LIKE '11' THEN 3
		WHEN lower(FVCDTOT) LIKE '12' THEN 3
		WHEN lower(FVCDTOT) LIKE '13' THEN 3
		WHEN lower(FVCDTOT) LIKE '14' THEN 4
		WHEN lower(FVCDTOT) LIKE '15' THEN 4
		WHEN lower(FVCDTOT) LIKE '16' THEN 4
		WHEN lower(FVCDTOT) LIKE '18.8' THEN 5
		WHEN lower(FVCDTOT) LIKE '17' THEN 5
		WHEN lower(FVCDTOT) LIKE '18.7' THEN 5
		WHEN lower(FVCDTOT) LIKE '18' THEN 5
		WHEN lower(FVCDTOT) LIKE '19' THEN 5
		WHEN lower(FVCDTOT) LIKE '10.6' THEN 3
		WHEN lower(FVCDTOT) LIKE '0' THEN 0
		WHEN lower(FVCDTOT) LIKE '10.5' THEN 3
		WHEN lower(FVCDTOT) LIKE '1' THEN 0
		WHEN lower(FVCDTOT) LIKE '10.4' THEN 3
		WHEN lower(FVCDTOT) LIKE '2' THEN 0
		WHEN lower(FVCDTOT) LIKE '10.3' THEN 3
		WHEN lower(FVCDTOT) LIKE '3' THEN 0
		WHEN lower(FVCDTOT) LIKE '10.2' THEN 3
		WHEN lower(FVCDTOT) LIKE '4' THEN 1
		WHEN lower(FVCDTOT) LIKE '10.1' THEN 2
		WHEN lower(FVCDTOT) LIKE '5' THEN 1
		WHEN lower(FVCDTOT) LIKE '6' THEN 1
		WHEN lower(FVCDTOT) LIKE '7' THEN 2
		WHEN lower(FVCDTOT) LIKE '18.6' THEN 5
		WHEN lower(FVCDTOT) LIKE '8' THEN 2
		WHEN lower(FVCDTOT) LIKE '18.5' THEN 5
		WHEN lower(FVCDTOT) LIKE '9' THEN 2
		WHEN lower(FVCDTOT) LIKE '18.4' THEN 5
		WHEN lower(FVCDTOT) LIKE '7.1' THEN 2
		WHEN lower(FVCDTOT) LIKE '18.3' THEN 5
		WHEN lower(FVCDTOT) LIKE '7.2' THEN 2
		WHEN lower(FVCDTOT) LIKE '18.2' THEN 5
		WHEN lower(FVCDTOT) LIKE '7.3' THEN 2
		WHEN lower(FVCDTOT) LIKE '18.1' THEN 5
		WHEN lower(FVCDTOT) LIKE '10.9' THEN 3
		WHEN lower(FVCDTOT) LIKE '7.4' THEN 2
		WHEN lower(FVCDTOT) LIKE '20' THEN 5
		WHEN lower(FVCDTOT) LIKE '10.8' THEN 3
		WHEN lower(FVCDTOT) LIKE '7.5' THEN 2
		WHEN lower(FVCDTOT) LIKE '33.6' THEN 9
		WHEN lower(FVCDTOT) LIKE '21' THEN 6
		WHEN lower(FVCDTOT) LIKE '10.7' THEN 3
		WHEN lower(FVCDTOT) LIKE '7.6' THEN 2
		WHEN lower(FVCDTOT) LIKE '22' THEN 6
		WHEN lower(FVCDTOT) LIKE '7.7' THEN 2
		WHEN lower(FVCDTOT) LIKE '23' THEN 6
		WHEN lower(FVCDTOT) LIKE '7.8' THEN 2
		WHEN lower(FVCDTOT) LIKE '7.9' THEN 2
		WHEN lower(FVCDTOT) LIKE '25' THEN 7
		WHEN lower(FVCDTOT) LIKE '29' THEN 8
		WHEN lower(FVCDTOT) LIKE '21.6' THEN 6
		WHEN lower(FVCDTOT) LIKE '21.4' THEN 6
		WHEN lower(FVCDTOT) LIKE '21.3' THEN 6
		WHEN lower(FVCDTOT) LIKE '21.2' THEN 6
		WHEN lower(FVCDTOT) LIKE '21.1' THEN 6
		WHEN lower(FVCDTOT) LIKE '29.6' THEN 8
		WHEN lower(FVCDTOT) LIKE '29.3' THEN 8
		WHEN lower(FVCDTOT) LIKE '34' THEN 10
		WHEN lower(FVCDTOT) LIKE '17.9' THEN 5
		WHEN lower(FVCDTOT) LIKE '17.8' THEN 5
		WHEN lower(FVCDTOT) LIKE '32.3' THEN 9
		WHEN lower(FVCDTOT) LIKE '17.7' THEN 5
		WHEN lower(FVCDTOT) LIKE '17.6' THEN 5
		WHEN lower(FVCDTOT) LIKE '6.1' THEN 1
		WHEN lower(FVCDTOT) LIKE '17.5' THEN 5
		WHEN lower(FVCDTOT) LIKE '6.2' THEN 1
		WHEN lower(FVCDTOT) LIKE '17.4' THEN 5
		WHEN lower(FVCDTOT) LIKE '6.3' THEN 1
		WHEN lower(FVCDTOT) LIKE '17.3' THEN 5
		WHEN lower(FVCDTOT) LIKE '6.4' THEN 1
		WHEN lower(FVCDTOT) LIKE '17.2' THEN 5
		WHEN lower(FVCDTOT) LIKE '6.5' THEN 1
		WHEN lower(FVCDTOT) LIKE '17.1' THEN 5
		WHEN lower(FVCDTOT) LIKE '6.6' THEN 1
		WHEN lower(FVCDTOT) LIKE '6.7' THEN 1
		WHEN lower(FVCDTOT) LIKE '6.8' THEN 2
		WHEN lower(FVCDTOT) LIKE '6.9' THEN 2
		WHEN lower(FVCDTOT) LIKE '20.7' THEN 6
		WHEN lower(FVCDTOT) LIKE '20.6' THEN 6
		WHEN lower(FVCDTOT) LIKE '20.4' THEN 6
		WHEN lower(FVCDTOT) LIKE '20.3' THEN 5
		WHEN lower(FVCDTOT) LIKE '20.1' THEN 5
		WHEN lower(FVCDTOT) LIKE '20.9' THEN 6
		WHEN lower(FVCDTOT) LIKE '28.1' THEN 8
		WHEN lower(FVCDTOT) LIKE '20.8' THEN 6
		WHEN lower(FVCDTOT) LIKE '16.9' THEN 4
		WHEN lower(FVCDTOT) LIKE '31.6' THEN 9
		WHEN lower(FVCDTOT) LIKE '31.1' THEN 9
		WHEN lower(FVCDTOT) LIKE '16.8' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.1' THEN 1
		WHEN lower(FVCDTOT) LIKE '16.7' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.2' THEN 1
		WHEN lower(FVCDTOT) LIKE '16.6' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.3' THEN 1
		WHEN lower(FVCDTOT) LIKE '16.5' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.4' THEN 1
		WHEN lower(FVCDTOT) LIKE '16.4' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.5' THEN 1
		WHEN lower(FVCDTOT) LIKE '16.3' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.6' THEN 1
		WHEN lower(FVCDTOT) LIKE '16.2' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.7' THEN 1
		WHEN lower(FVCDTOT) LIKE '16.1' THEN 4
		WHEN lower(FVCDTOT) LIKE '5.8' THEN 1
		WHEN lower(FVCDTOT) LIKE '5.9' THEN 1
		WHEN lower(FVCDTOT) LIKE '27.9' THEN 8
		WHEN lower(FVCDTOT) LIKE '15.1' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.1' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.9' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.2' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.8' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.3' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.7' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.4' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.6' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.5' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.5' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.6' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.4' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.7' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.3' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.8' THEN 1
		WHEN lower(FVCDTOT) LIKE '15.2' THEN 4
		WHEN lower(FVCDTOT) LIKE '4.9' THEN 1
		WHEN lower(FVCDTOT) LIKE '26.9' THEN 7
		WHEN lower(FVCDTOT) LIKE '26.6' THEN 7
		WHEN lower(FVCDTOT) LIKE '26.4' THEN 7
		WHEN lower(FVCDTOT) LIKE '26.3' THEN 7
		WHEN lower(FVCDTOT) LIKE '26.2' THEN 7
		WHEN lower(FVCDTOT) LIKE '14.2' THEN 4
		WHEN lower(FVCDTOT) LIKE '14.1' THEN 4
		WHEN lower(FVCDTOT) LIKE '3.1' THEN 0
		WHEN lower(FVCDTOT) LIKE '3.2' THEN 0
		WHEN lower(FVCDTOT) LIKE '3.3' THEN 0
		WHEN lower(FVCDTOT) LIKE '14.9' THEN 4
		WHEN lower(FVCDTOT) LIKE '3.4' THEN 1
		WHEN lower(FVCDTOT) LIKE '14.8' THEN 4
		WHEN lower(FVCDTOT) LIKE '3.5' THEN 1
		WHEN lower(FVCDTOT) LIKE '14.7' THEN 4
		WHEN lower(FVCDTOT) LIKE '3.6' THEN 1
		WHEN lower(FVCDTOT) LIKE '14.6' THEN 4
		WHEN lower(FVCDTOT) LIKE '3.7' THEN 1
		WHEN lower(FVCDTOT) LIKE '14.5' THEN 4
		WHEN lower(FVCDTOT) LIKE '3.8' THEN 1
		WHEN lower(FVCDTOT) LIKE '14.4' THEN 4
		WHEN lower(FVCDTOT) LIKE '3.9' THEN 1
		WHEN lower(FVCDTOT) LIKE '14.3' THEN 4
		WHEN lower(FVCDTOT) LIKE '25.1' THEN 7
		WHEN lower(FVCDTOT) LIKE '25.9' THEN 7
		WHEN lower(FVCDTOT) LIKE '25.7' THEN 7
		WHEN lower(FVCDTOT) LIKE '13.3' THEN 3
		WHEN lower(FVCDTOT) LIKE '13.2' THEN 3
		WHEN lower(FVCDTOT) LIKE '13.1' THEN 3
		WHEN lower(FVCDTOT) LIKE '2.1' THEN 0
		WHEN lower(FVCDTOT) LIKE '2.2' THEN 0
		WHEN lower(FVCDTOT) LIKE '2.3' THEN 0
		WHEN lower(FVCDTOT) LIKE '2.4' THEN 0
		WHEN lower(FVCDTOT) LIKE '2.5' THEN 0
		WHEN lower(FVCDTOT) LIKE '13.9' THEN 4
		WHEN lower(FVCDTOT) LIKE '2.6' THEN 0
		WHEN lower(FVCDTOT) LIKE '13.8' THEN 4
		WHEN lower(FVCDTOT) LIKE '2.7' THEN 0
		WHEN lower(FVCDTOT) LIKE '13.7' THEN 4
		WHEN lower(FVCDTOT) LIKE '2.8' THEN 0
		WHEN lower(FVCDTOT) LIKE '13.6' THEN 4
		WHEN lower(FVCDTOT) LIKE '2.9' THEN 0
		WHEN lower(FVCDTOT) LIKE '13.5' THEN 3
		WHEN lower(FVCDTOT) LIKE '13.4' THEN 3
		WHEN lower(FVCDTOT) LIKE '24.3' THEN 7
		WHEN lower(FVCDTOT) LIKE '24.9' THEN 7
		WHEN lower(FVCDTOT) LIKE '24.7' THEN 7
		WHEN lower(FVCDTOT) LIKE '24.6' THEN 7
		WHEN lower(FVCDTOT) LIKE '24.4' THEN 7
		WHEN lower(FVCDTOT) LIKE '12.4' THEN 3
		WHEN lower(FVCDTOT) LIKE '12.3' THEN 3
		WHEN lower(FVCDTOT) LIKE '12.2' THEN 3
		WHEN lower(FVCDTOT) LIKE '12.1' THEN 3
		WHEN lower(FVCDTOT) LIKE '1.1' THEN 0
		WHEN lower(FVCDTOT) LIKE '1.2' THEN 0
		WHEN lower(FVCDTOT) LIKE '1.3' THEN 0
		WHEN lower(FVCDTOT) LIKE '1.4' THEN 0
		WHEN lower(FVCDTOT) LIKE '1.5' THEN 0
		WHEN lower(FVCDTOT) LIKE '1.6' THEN 0
		WHEN lower(FVCDTOT) LIKE '1.7' THEN 0
		WHEN lower(FVCDTOT) LIKE '12.9' THEN 3
		WHEN lower(FVCDTOT) LIKE '1.8' THEN 0
		WHEN lower(FVCDTOT) LIKE '12.8' THEN 3
		WHEN lower(FVCDTOT) LIKE '9.1' THEN 2
		WHEN lower(FVCDTOT) LIKE '1.9' THEN 0
		WHEN lower(FVCDTOT) LIKE '12.7' THEN 3
		WHEN lower(FVCDTOT) LIKE '9.2' THEN 2
		WHEN lower(FVCDTOT) LIKE '12.6' THEN 3
		WHEN lower(FVCDTOT) LIKE '9.3' THEN 2
		WHEN lower(FVCDTOT) LIKE '12.5' THEN 3
		WHEN lower(FVCDTOT) LIKE '9.4' THEN 2
		WHEN lower(FVCDTOT) LIKE '9.5' THEN 2
		WHEN lower(FVCDTOT) LIKE '9.6' THEN 2
		WHEN lower(FVCDTOT) LIKE '9.7' THEN 2
		WHEN lower(FVCDTOT) LIKE '9.8' THEN 2
		WHEN lower(FVCDTOT) LIKE '9.9' THEN 2
		WHEN lower(FVCDTOT) LIKE '23.3' THEN 6
		WHEN lower(FVCDTOT) LIKE '23.2' THEN 6
		WHEN lower(FVCDTOT) LIKE '23.1' THEN 6
		WHEN lower(FVCDTOT) LIKE '23.7' THEN 6
		WHEN lower(FVCDTOT) LIKE '23.6' THEN 6
		WHEN lower(FVCDTOT) LIKE '19.8' THEN 5
		WHEN lower(FVCDTOT) LIKE '19.7' THEN 5
		WHEN lower(FVCDTOT) LIKE '19.6' THEN 5
		WHEN lower(FVCDTOT) LIKE '11.5' THEN 3
		WHEN lower(FVCDTOT) LIKE '11.4' THEN 3
		WHEN lower(FVCDTOT) LIKE '11.3' THEN 3
		WHEN lower(FVCDTOT) LIKE '11.2' THEN 3
		WHEN lower(FVCDTOT) LIKE '0.1' THEN 0
		WHEN lower(FVCDTOT) LIKE '11.1' THEN 3
		WHEN lower(FVCDTOT) LIKE '0.2' THEN 0
		WHEN lower(FVCDTOT) LIKE '0.3' THEN 0
		WHEN lower(FVCDTOT) LIKE '0.4' THEN 0
		WHEN lower(FVCDTOT) LIKE '0.5' THEN 0
		WHEN lower(FVCDTOT) LIKE '19.5' THEN 5
		WHEN lower(FVCDTOT) LIKE '0.6' THEN 0
		WHEN lower(FVCDTOT) LIKE '19.4' THEN 5
		WHEN lower(FVCDTOT) LIKE '0.7' THEN 0
		WHEN lower(FVCDTOT) LIKE '19.3' THEN 5
		WHEN lower(FVCDTOT) LIKE '0.8' THEN 0
		WHEN lower(FVCDTOT) LIKE '19.2' THEN 5
		WHEN lower(FVCDTOT) LIKE '8.1' THEN 2
		WHEN lower(FVCDTOT) LIKE '0.9' THEN 0
		WHEN lower(FVCDTOT) LIKE '19.1' THEN 5
		WHEN lower(FVCDTOT) LIKE '11.9' THEN 3
		WHEN lower(FVCDTOT) LIKE '8.2' THEN 2
		WHEN lower(FVCDTOT) LIKE '11.8' THEN 3
		WHEN lower(FVCDTOT) LIKE '8.3' THEN 2
		WHEN lower(FVCDTOT) LIKE '11.7' THEN 3
		WHEN lower(FVCDTOT) LIKE '8.4' THEN 2
		WHEN lower(FVCDTOT) LIKE '11.6' THEN 3
		WHEN lower(FVCDTOT) LIKE '8.5' THEN 2
		WHEN lower(FVCDTOT) LIKE 'don''t know' THEN null 
		WHEN lower(FVCDTOT) LIKE 'n/s' THEN null 
		WHEN lower(FVCDTOT) LIKE 'n/a' THEN null 
		WHEN lower(FVCDTOT) LIKE 'refusal' THEN null 
		WHEN lower(FVCDTOT) LIKE 'no drinks last w' THEN null 
		WHEN lower(FVCDTOT) LIKE 'no phy. activity' THEN null 
		WHEN lower(FVCDTOT) LIKE 'not required' THEN null 
	END AS FVCDTOT,
	CASE
		WHEN lower(GEN_02A2) LIKE '1' THEN 1
		WHEN lower(GEN_02A2) LIKE '2' THEN 2
		WHEN lower(GEN_02A2) LIKE '3' THEN 3
		WHEN lower(GEN_02A2) LIKE '4' THEN 4
		WHEN lower(GEN_02A2) LIKE '5' THEN 5
		WHEN lower(GEN_02A2) LIKE '6' THEN 6
		WHEN lower(GEN_02A2) LIKE '7' THEN 7
		WHEN lower(GEN_02A2) LIKE '8' THEN 8
		WHEN lower(GEN_02A2) LIKE '9' THEN 9
		WHEN lower(GEN_02A2) LIKE 'very satisfied' THEN 10
		WHEN lower(GEN_02A2) LIKE 'very dissatisfie' THEN 0
		WHEN lower(GEN_02A2) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_02A2) LIKE 'n/s' THEN null 
		WHEN lower(GEN_02A2) LIKE 'n/a' THEN null 
		WHEN lower(GEN_02A2) LIKE 'refusal' THEN null 
		WHEN lower(GEN_02A2) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_02A2) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_02A2) LIKE 'not required' THEN null 
	END AS GEN_02A2,
	CASE
		WHEN lower(GEN_08) LIKE 'no' THEN 0
		WHEN lower(GEN_08) LIKE 'yes' THEN 10
		WHEN lower(GEN_08) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_08) LIKE 'n/s' THEN null 
		WHEN lower(GEN_08) LIKE 'n/a' THEN null 
		WHEN lower(GEN_08) LIKE 'refusal' THEN null 
		WHEN lower(GEN_08) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_08) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_08) LIKE 'not required' THEN null 
	END AS GEN_08,
	CASE
		WHEN lower(PACDFM) LIKE '110' THEN 3
		WHEN lower(PACDFM) LIKE '232' THEN 7
		WHEN lower(PACDFM) LIKE '111' THEN 3
		WHEN lower(PACDFM) LIKE '112' THEN 3
		WHEN lower(PACDFM) LIKE '113' THEN 3
		WHEN lower(PACDFM) LIKE '114' THEN 3
		WHEN lower(PACDFM) LIKE '236' THEN 7
		WHEN lower(PACDFM) LIKE '115' THEN 3
		WHEN lower(PACDFM) LIKE '237' THEN 7
		WHEN lower(PACDFM) LIKE '116' THEN 3
		WHEN lower(PACDFM) LIKE '238' THEN 7
		WHEN lower(PACDFM) LIKE '117' THEN 3
		WHEN lower(PACDFM) LIKE '239' THEN 7
		WHEN lower(PACDFM) LIKE '118' THEN 3
		WHEN lower(PACDFM) LIKE '119' THEN 3
		WHEN lower(PACDFM) LIKE '10' THEN 0
		WHEN lower(PACDFM) LIKE '11' THEN 0
		WHEN lower(PACDFM) LIKE '12' THEN 0
		WHEN lower(PACDFM) LIKE '13' THEN 0
		WHEN lower(PACDFM) LIKE '14' THEN 0
		WHEN lower(PACDFM) LIKE '15' THEN 0
		WHEN lower(PACDFM) LIKE '16' THEN 0
		WHEN lower(PACDFM) LIKE '17' THEN 0
		WHEN lower(PACDFM) LIKE '18' THEN 0
		WHEN lower(PACDFM) LIKE '19' THEN 0
		WHEN lower(PACDFM) LIKE '240' THEN 7
		WHEN lower(PACDFM) LIKE '120' THEN 3
		WHEN lower(PACDFM) LIKE '242' THEN 7
		WHEN lower(PACDFM) LIKE '121' THEN 3
		WHEN lower(PACDFM) LIKE '0' THEN 0
		WHEN lower(PACDFM) LIKE '122' THEN 3
		WHEN lower(PACDFM) LIKE '1' THEN 0
		WHEN lower(PACDFM) LIKE '123' THEN 3
		WHEN lower(PACDFM) LIKE '2' THEN 0
		WHEN lower(PACDFM) LIKE '245' THEN 7
		WHEN lower(PACDFM) LIKE '124' THEN 3
		WHEN lower(PACDFM) LIKE '3' THEN 0
		WHEN lower(PACDFM) LIKE '125' THEN 3
		WHEN lower(PACDFM) LIKE '4' THEN 0
		WHEN lower(PACDFM) LIKE '126' THEN 3
		WHEN lower(PACDFM) LIKE '5' THEN 0
		WHEN lower(PACDFM) LIKE '127' THEN 3
		WHEN lower(PACDFM) LIKE '6' THEN 0
		WHEN lower(PACDFM) LIKE '128' THEN 3
		WHEN lower(PACDFM) LIKE '7' THEN 0
		WHEN lower(PACDFM) LIKE '129' THEN 3
		WHEN lower(PACDFM) LIKE '8' THEN 0
		WHEN lower(PACDFM) LIKE '9' THEN 0
		WHEN lower(PACDFM) LIKE '20' THEN 0
		WHEN lower(PACDFM) LIKE '21' THEN 0
		WHEN lower(PACDFM) LIKE '22' THEN 0
		WHEN lower(PACDFM) LIKE '23' THEN 0
		WHEN lower(PACDFM) LIKE '24' THEN 0
		WHEN lower(PACDFM) LIKE '25' THEN 0
		WHEN lower(PACDFM) LIKE '26' THEN 0
		WHEN lower(PACDFM) LIKE '27' THEN 0
		WHEN lower(PACDFM) LIKE '28' THEN 0
		WHEN lower(PACDFM) LIKE '29' THEN 0
		WHEN lower(PACDFM) LIKE '251' THEN 7
		WHEN lower(PACDFM) LIKE '130' THEN 4
		WHEN lower(PACDFM) LIKE '131' THEN 4
		WHEN lower(PACDFM) LIKE '132' THEN 4
		WHEN lower(PACDFM) LIKE '133' THEN 4
		WHEN lower(PACDFM) LIKE '134' THEN 4
		WHEN lower(PACDFM) LIKE '256' THEN 7
		WHEN lower(PACDFM) LIKE '135' THEN 4
		WHEN lower(PACDFM) LIKE '257' THEN 7
		WHEN lower(PACDFM) LIKE '136' THEN 4
		WHEN lower(PACDFM) LIKE '137' THEN 4
		WHEN lower(PACDFM) LIKE '138' THEN 4
		WHEN lower(PACDFM) LIKE '139' THEN 4
		WHEN lower(PACDFM) LIKE '30' THEN 0
		WHEN lower(PACDFM) LIKE '31' THEN 0
		WHEN lower(PACDFM) LIKE '32' THEN 0
		WHEN lower(PACDFM) LIKE '33' THEN 1
		WHEN lower(PACDFM) LIKE '34' THEN 1
		WHEN lower(PACDFM) LIKE '35' THEN 1
		WHEN lower(PACDFM) LIKE '36' THEN 1
		WHEN lower(PACDFM) LIKE '37' THEN 1
		WHEN lower(PACDFM) LIKE '38' THEN 1
		WHEN lower(PACDFM) LIKE '39' THEN 1
		WHEN lower(PACDFM) LIKE '140' THEN 4
		WHEN lower(PACDFM) LIKE '141' THEN 4
		WHEN lower(PACDFM) LIKE '263' THEN 8
		WHEN lower(PACDFM) LIKE '142' THEN 4
		WHEN lower(PACDFM) LIKE '143' THEN 4
		WHEN lower(PACDFM) LIKE '144' THEN 4
		WHEN lower(PACDFM) LIKE '145' THEN 4
		WHEN lower(PACDFM) LIKE '267' THEN 8
		WHEN lower(PACDFM) LIKE '146' THEN 4
		WHEN lower(PACDFM) LIKE '147' THEN 4
		WHEN lower(PACDFM) LIKE '148' THEN 4
		WHEN lower(PACDFM) LIKE '149' THEN 4
		WHEN lower(PACDFM) LIKE '40' THEN 1
		WHEN lower(PACDFM) LIKE '41' THEN 1
		WHEN lower(PACDFM) LIKE '42' THEN 1
		WHEN lower(PACDFM) LIKE '43' THEN 1
		WHEN lower(PACDFM) LIKE '44' THEN 1
		WHEN lower(PACDFM) LIKE '45' THEN 1
		WHEN lower(PACDFM) LIKE '46' THEN 1
		WHEN lower(PACDFM) LIKE '47' THEN 1
		WHEN lower(PACDFM) LIKE '48' THEN 1
		WHEN lower(PACDFM) LIKE '49' THEN 1
		WHEN lower(PACDFM) LIKE '271' THEN 8
		WHEN lower(PACDFM) LIKE '150' THEN 4
		WHEN lower(PACDFM) LIKE '151' THEN 4
		WHEN lower(PACDFM) LIKE '273' THEN 8
		WHEN lower(PACDFM) LIKE '152' THEN 4
		WHEN lower(PACDFM) LIKE '153' THEN 4
		WHEN lower(PACDFM) LIKE '154' THEN 4
		WHEN lower(PACDFM) LIKE '155' THEN 4
		WHEN lower(PACDFM) LIKE '156' THEN 4
		WHEN lower(PACDFM) LIKE '278' THEN 8
		WHEN lower(PACDFM) LIKE '157' THEN 4
		WHEN lower(PACDFM) LIKE '158' THEN 4
		WHEN lower(PACDFM) LIKE '159' THEN 4
		WHEN lower(PACDFM) LIKE '50' THEN 1
		WHEN lower(PACDFM) LIKE '51' THEN 1
		WHEN lower(PACDFM) LIKE '52' THEN 1
		WHEN lower(PACDFM) LIKE '53' THEN 1
		WHEN lower(PACDFM) LIKE '54' THEN 1
		WHEN lower(PACDFM) LIKE '55' THEN 1
		WHEN lower(PACDFM) LIKE '56' THEN 1
		WHEN lower(PACDFM) LIKE '57' THEN 1
		WHEN lower(PACDFM) LIKE '58' THEN 1
		WHEN lower(PACDFM) LIKE '59' THEN 1
		WHEN lower(PACDFM) LIKE '160' THEN 4
		WHEN lower(PACDFM) LIKE '161' THEN 4
		WHEN lower(PACDFM) LIKE '162' THEN 4
		WHEN lower(PACDFM) LIKE '163' THEN 5
		WHEN lower(PACDFM) LIKE '164' THEN 5
		WHEN lower(PACDFM) LIKE '165' THEN 5
		WHEN lower(PACDFM) LIKE '166' THEN 5
		WHEN lower(PACDFM) LIKE '167' THEN 5
		WHEN lower(PACDFM) LIKE '168' THEN 5
		WHEN lower(PACDFM) LIKE '169' THEN 5
		WHEN lower(PACDFM) LIKE '60' THEN 1
		WHEN lower(PACDFM) LIKE '61' THEN 1
		WHEN lower(PACDFM) LIKE '62' THEN 1
		WHEN lower(PACDFM) LIKE '63' THEN 1
		WHEN lower(PACDFM) LIKE '64' THEN 1
		WHEN lower(PACDFM) LIKE '65' THEN 2
		WHEN lower(PACDFM) LIKE '66' THEN 2
		WHEN lower(PACDFM) LIKE '67' THEN 2
		WHEN lower(PACDFM) LIKE '68' THEN 2
		WHEN lower(PACDFM) LIKE '69' THEN 2
		WHEN lower(PACDFM) LIKE '170' THEN 5
		WHEN lower(PACDFM) LIKE '171' THEN 5
		WHEN lower(PACDFM) LIKE '172' THEN 5
		WHEN lower(PACDFM) LIKE '173' THEN 5
		WHEN lower(PACDFM) LIKE '174' THEN 5
		WHEN lower(PACDFM) LIKE '176' THEN 5
		WHEN lower(PACDFM) LIKE '177' THEN 5
		WHEN lower(PACDFM) LIKE '178' THEN 5
		WHEN lower(PACDFM) LIKE '179' THEN 5
		WHEN lower(PACDFM) LIKE '70' THEN 2
		WHEN lower(PACDFM) LIKE '71' THEN 2
		WHEN lower(PACDFM) LIKE '72' THEN 2
		WHEN lower(PACDFM) LIKE '73' THEN 2
		WHEN lower(PACDFM) LIKE '74' THEN 2
		WHEN lower(PACDFM) LIKE '75' THEN 2
		WHEN lower(PACDFM) LIKE '76' THEN 2
		WHEN lower(PACDFM) LIKE '77' THEN 2
		WHEN lower(PACDFM) LIKE '78' THEN 2
		WHEN lower(PACDFM) LIKE '79' THEN 2
		WHEN lower(PACDFM) LIKE '180' THEN 5
		WHEN lower(PACDFM) LIKE '181' THEN 5
		WHEN lower(PACDFM) LIKE '182' THEN 5
		WHEN lower(PACDFM) LIKE '183' THEN 5
		WHEN lower(PACDFM) LIKE '184' THEN 5
		WHEN lower(PACDFM) LIKE '185' THEN 5
		WHEN lower(PACDFM) LIKE '186' THEN 5
		WHEN lower(PACDFM) LIKE '188' THEN 5
		WHEN lower(PACDFM) LIKE '189' THEN 5
		WHEN lower(PACDFM) LIKE '80' THEN 2
		WHEN lower(PACDFM) LIKE '81' THEN 2
		WHEN lower(PACDFM) LIKE '82' THEN 2
		WHEN lower(PACDFM) LIKE '83' THEN 2
		WHEN lower(PACDFM) LIKE '84' THEN 2
		WHEN lower(PACDFM) LIKE '85' THEN 2
		WHEN lower(PACDFM) LIKE '86' THEN 2
		WHEN lower(PACDFM) LIKE '87' THEN 2
		WHEN lower(PACDFM) LIKE '88' THEN 2
		WHEN lower(PACDFM) LIKE '89' THEN 2
		WHEN lower(PACDFM) LIKE '190' THEN 5
		WHEN lower(PACDFM) LIKE '192' THEN 5
		WHEN lower(PACDFM) LIKE '193' THEN 5
		WHEN lower(PACDFM) LIKE '195' THEN 6
		WHEN lower(PACDFM) LIKE '198' THEN 6
		WHEN lower(PACDFM) LIKE '199' THEN 6
		WHEN lower(PACDFM) LIKE '90' THEN 2
		WHEN lower(PACDFM) LIKE '91' THEN 2
		WHEN lower(PACDFM) LIKE '92' THEN 2
		WHEN lower(PACDFM) LIKE '93' THEN 2
		WHEN lower(PACDFM) LIKE '94' THEN 2
		WHEN lower(PACDFM) LIKE '95' THEN 2
		WHEN lower(PACDFM) LIKE '96' THEN 2
		WHEN lower(PACDFM) LIKE '97' THEN 2
		WHEN lower(PACDFM) LIKE '98' THEN 3
		WHEN lower(PACDFM) LIKE '99' THEN 3
		WHEN lower(PACDFM) LIKE '201' THEN 6
		WHEN lower(PACDFM) LIKE '202' THEN 6
		WHEN lower(PACDFM) LIKE '203' THEN 6
		WHEN lower(PACDFM) LIKE '325' THEN 10
		WHEN lower(PACDFM) LIKE '205' THEN 6
		WHEN lower(PACDFM) LIKE '206' THEN 6
		WHEN lower(PACDFM) LIKE '207' THEN 6
		WHEN lower(PACDFM) LIKE '209' THEN 6
		WHEN lower(PACDFM) LIKE '210' THEN 6
		WHEN lower(PACDFM) LIKE '216' THEN 6
		WHEN lower(PACDFM) LIKE '217' THEN 6
		WHEN lower(PACDFM) LIKE '218' THEN 6
		WHEN lower(PACDFM) LIKE '100' THEN 3
		WHEN lower(PACDFM) LIKE '222' THEN 6
		WHEN lower(PACDFM) LIKE '101' THEN 3
		WHEN lower(PACDFM) LIKE '102' THEN 3
		WHEN lower(PACDFM) LIKE '103' THEN 3
		WHEN lower(PACDFM) LIKE '225' THEN 6
		WHEN lower(PACDFM) LIKE '104' THEN 3
		WHEN lower(PACDFM) LIKE '105' THEN 3
		WHEN lower(PACDFM) LIKE '106' THEN 3
		WHEN lower(PACDFM) LIKE '228' THEN 7
		WHEN lower(PACDFM) LIKE '107' THEN 3
		WHEN lower(PACDFM) LIKE '229' THEN 7
		WHEN lower(PACDFM) LIKE '108' THEN 3
		WHEN lower(PACDFM) LIKE '109' THEN 3
		WHEN lower(PACDFM) LIKE 'don''t know' THEN null 
		WHEN lower(PACDFM) LIKE 'n/s' THEN null 
		WHEN lower(PACDFM) LIKE 'n/a' THEN null 
		WHEN lower(PACDFM) LIKE 'refusal' THEN null 
		WHEN lower(PACDFM) LIKE 'no drinks last w' THEN null 
		WHEN lower(PACDFM) LIKE 'no phy. activity' THEN null 
		WHEN lower(PACDFM) LIKE 'not required' THEN null 
	END AS PACDFM,
	CASE
		WHEN lower(SPSDCON) LIKE '22' THEN 7
		WHEN lower(SPSDCON) LIKE '23' THEN 7
		WHEN lower(SPSDCON) LIKE '24' THEN 8
		WHEN lower(SPSDCON) LIKE '25' THEN 8
		WHEN lower(SPSDCON) LIKE '26' THEN 8
		WHEN lower(SPSDCON) LIKE '27' THEN 9
		WHEN lower(SPSDCON) LIKE '28' THEN 9
		WHEN lower(SPSDCON) LIKE '29' THEN 9
		WHEN lower(SPSDCON) LIKE '30' THEN 10
		WHEN lower(SPSDCON) LIKE '31' THEN 10
		WHEN lower(SPSDCON) LIKE '10' THEN 3
		WHEN lower(SPSDCON) LIKE '32' THEN 10
		WHEN lower(SPSDCON) LIKE '11' THEN 3
		WHEN lower(SPSDCON) LIKE '33' THEN 11
		WHEN lower(SPSDCON) LIKE '12' THEN 4
		WHEN lower(SPSDCON) LIKE '34' THEN 11
		WHEN lower(SPSDCON) LIKE '13' THEN 4
		WHEN lower(SPSDCON) LIKE '35' THEN 11
		WHEN lower(SPSDCON) LIKE '14' THEN 4
		WHEN lower(SPSDCON) LIKE '36' THEN 12
		WHEN lower(SPSDCON) LIKE '15' THEN 5
		WHEN lower(SPSDCON) LIKE '37' THEN 12
		WHEN lower(SPSDCON) LIKE '16' THEN 5
		WHEN lower(SPSDCON) LIKE '38' THEN 12
		WHEN lower(SPSDCON) LIKE '17' THEN 5
		WHEN lower(SPSDCON) LIKE '39' THEN 13
		WHEN lower(SPSDCON) LIKE '18' THEN 6
		WHEN lower(SPSDCON) LIKE '19' THEN 6
		WHEN lower(SPSDCON) LIKE '40' THEN 13
		WHEN lower(SPSDCON) LIKE '20' THEN 6
		WHEN lower(SPSDCON) LIKE '21' THEN 7
		WHEN lower(SPSDCON) LIKE 'don''t know' THEN null 
		WHEN lower(SPSDCON) LIKE 'n/s' THEN null 
		WHEN lower(SPSDCON) LIKE 'n/a' THEN null 
		WHEN lower(SPSDCON) LIKE 'refusal' THEN null 
		WHEN lower(SPSDCON) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPSDCON) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPSDCON) LIKE 'not required' THEN null 
	END AS SPSDCON,
	CASE
		WHEN lower(PCU_153) LIKE '<once every 3 yr' THEN 8
		WHEN lower(PCU_153) LIKE 'once every 3 yrs' THEN 10
		WHEN lower(PCU_153) LIKE 'once every 2 yrs' THEN 6
		WHEN lower(PCU_153) LIKE 'once a year' THEN 2
		WHEN lower(PCU_153) LIKE '> once a year' THEN 4
		WHEN lower(PCU_153) LIKE 'no reg. pattern' THEN 0
		WHEN lower(PCU_153) LIKE 'don''t know' THEN null 
		WHEN lower(PCU_153) LIKE 'n/s' THEN null 
		WHEN lower(PCU_153) LIKE 'n/a' THEN null 
		WHEN lower(PCU_153) LIKE 'refusal' THEN null 
		WHEN lower(PCU_153) LIKE 'no drinks last w' THEN null 
		WHEN lower(PCU_153) LIKE 'no phy. activity' THEN null 
		WHEN lower(PCU_153) LIKE 'not required' THEN null 
	END AS PCU_153,
	CASE
		WHEN lower(GENGSWL) LIKE 'neither nor' THEN 5
		WHEN lower(GENGSWL) LIKE 'satisfied' THEN 7
		WHEN lower(GENGSWL) LIKE 'dissatisfied' THEN 2
		WHEN lower(GENGSWL) LIKE 'very satisfied' THEN 10
		WHEN lower(GENGSWL) LIKE 'very dissatis.' THEN 0
		WHEN lower(GENGSWL) LIKE 'don''t know' THEN null 
		WHEN lower(GENGSWL) LIKE 'n/s' THEN null 
		WHEN lower(GENGSWL) LIKE 'n/a' THEN null 
		WHEN lower(GENGSWL) LIKE 'refusal' THEN null 
		WHEN lower(GENGSWL) LIKE 'no drinks last w' THEN null 
		WHEN lower(GENGSWL) LIKE 'no phy. activity' THEN null 
		WHEN lower(GENGSWL) LIKE 'not required' THEN null 
	END AS GENGSWL,
	CASE
		WHEN lower(ADLF6R) LIKE 'no' THEN 0
		WHEN lower(ADLF6R) LIKE 'yes' THEN 10
		WHEN lower(ADLF6R) LIKE 'don''t know' THEN null 
		WHEN lower(ADLF6R) LIKE 'n/s' THEN null 
		WHEN lower(ADLF6R) LIKE 'n/a' THEN null 
		WHEN lower(ADLF6R) LIKE 'refusal' THEN null 
		WHEN lower(ADLF6R) LIKE 'no drinks last w' THEN null 
		WHEN lower(ADLF6R) LIKE 'no phy. activity' THEN null 
		WHEN lower(ADLF6R) LIKE 'not required' THEN null 
	END AS ADLF6R,
	CASE
		WHEN lower(INCGHH) LIKE '$20,000-$39,999' THEN 5
		WHEN lower(INCGHH) LIKE 'no or <$20,000' THEN 2
		WHEN lower(INCGHH) LIKE '$40,000-$59,999' THEN 7
		WHEN lower(INCGHH) LIKE '$60,000-$79,999' THEN 10
		WHEN lower(INCGHH) LIKE '$80,000 or more' THEN 12
		WHEN lower(INCGHH) LIKE 'don''t know' THEN null 
		WHEN lower(INCGHH) LIKE 'n/s' THEN null 
		WHEN lower(INCGHH) LIKE 'n/a' THEN null 
		WHEN lower(INCGHH) LIKE 'refusal' THEN null 
		WHEN lower(INCGHH) LIKE 'no drinks last w' THEN null 
		WHEN lower(INCGHH) LIKE 'no phy. activity' THEN null 
		WHEN lower(INCGHH) LIKE 'not required' THEN null 
	END AS INCGHH,
	CASE
		WHEN lower(GENDHDI) LIKE 'very good' THEN 10
		WHEN lower(GENDHDI) LIKE 'excellent' THEN 12
		WHEN lower(GENDHDI) LIKE 'poor' THEN 2
		WHEN lower(GENDHDI) LIKE 'fair' THEN 5
		WHEN lower(GENDHDI) LIKE 'good' THEN 7
		WHEN lower(GENDHDI) LIKE 'don''t know' THEN null 
		WHEN lower(GENDHDI) LIKE 'n/s' THEN null 
		WHEN lower(GENDHDI) LIKE 'n/a' THEN null 
		WHEN lower(GENDHDI) LIKE 'refusal' THEN null 
		WHEN lower(GENDHDI) LIKE 'no drinks last w' THEN null 
		WHEN lower(GENDHDI) LIKE 'no phy. activity' THEN null 
		WHEN lower(GENDHDI) LIKE 'not required' THEN null 
	END AS GENDHDI,
	CASE
		WHEN lower(PMH_04) LIKE 'once or twice' THEN 2
		WHEN lower(PMH_04) LIKE 'never' THEN 0
		WHEN lower(PMH_04) LIKE '2 or 3 times\wk' THEN 6
		WHEN lower(PMH_04) LIKE 'every day' THEN 10
		WHEN lower(PMH_04) LIKE 'almost every day' THEN 8
		WHEN lower(PMH_04) LIKE 'once a week' THEN 4
		WHEN lower(PMH_04) LIKE 'don''t know' THEN null 
		WHEN lower(PMH_04) LIKE 'n/s' THEN null 
		WHEN lower(PMH_04) LIKE 'n/a' THEN null 
		WHEN lower(PMH_04) LIKE 'refusal' THEN null 
		WHEN lower(PMH_04) LIKE 'no drinks last w' THEN null 
		WHEN lower(PMH_04) LIKE 'no phy. activity' THEN null 
		WHEN lower(PMH_04) LIKE 'not required' THEN null 
	END AS PMH_04,
	CASE
		WHEN lower(GEN_02B) LIKE 'very good' THEN 10
		WHEN lower(GEN_02B) LIKE 'excellent' THEN 12
		WHEN lower(GEN_02B) LIKE 'poor' THEN 2
		WHEN lower(GEN_02B) LIKE 'fair' THEN 5
		WHEN lower(GEN_02B) LIKE 'good' THEN 7
		WHEN lower(GEN_02B) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_02B) LIKE 'n/s' THEN null 
		WHEN lower(GEN_02B) LIKE 'n/a' THEN null 
		WHEN lower(GEN_02B) LIKE 'refusal' THEN null 
		WHEN lower(GEN_02B) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_02B) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_02B) LIKE 'not required' THEN null 
	END AS GEN_02B,
	CASE
		WHEN lower(GENDMHI) LIKE 'very good' THEN 10
		WHEN lower(GENDMHI) LIKE 'excellent' THEN 12
		WHEN lower(GENDMHI) LIKE 'poor' THEN 2
		WHEN lower(GENDMHI) LIKE 'fair' THEN 5
		WHEN lower(GENDMHI) LIKE 'good' THEN 7
		WHEN lower(GENDMHI) LIKE 'don''t know' THEN null 
		WHEN lower(GENDMHI) LIKE 'n/s' THEN null 
		WHEN lower(GENDMHI) LIKE 'n/a' THEN null 
		WHEN lower(GENDMHI) LIKE 'refusal' THEN null 
		WHEN lower(GENDMHI) LIKE 'no drinks last w' THEN null 
		WHEN lower(GENDMHI) LIKE 'no phy. activity' THEN null 
		WHEN lower(GENDMHI) LIKE 'not required' THEN null 
	END AS GENDMHI
INTO segments_map
FROM segments LIMIT 3