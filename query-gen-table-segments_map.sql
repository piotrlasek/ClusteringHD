SELECT
	ID,
	CASE
		WHEN lower(SPV_6B) LIKE 'once a month' THEN 3
		WHEN lower(SPV_6B) LIKE 'not at all' THEN 0
		WHEN lower(SPV_6B) LIKE 'once /twice a yr' THEN 2
		WHEN lower(SPV_6B) LIKE 'once a year' THEN 1
		WHEN lower(SPV_6B) LIKE 'once a day' THEN 5
		WHEN lower(SPV_6B) LIKE 'once a week' THEN 4
		WHEN lower(SPV_6B) LIKE 'don''t know' THEN null 
		WHEN lower(SPV_6B) LIKE 'n/s' THEN null 
		WHEN lower(SPV_6B) LIKE 'n/a' THEN null 
		WHEN lower(SPV_6B) LIKE 'refusal' THEN null 
		WHEN lower(SPV_6B) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPV_6B) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPV_6B) LIKE 'not required' THEN null 
	END AS SPV_6B,
	CASE
		WHEN lower(GEODPMF) LIKE 'york regional h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'sunrise/kelsey' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'haliburton-kaw.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'région de laval' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'pei' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. outaouais' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég.montreal' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'colchester east' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'sudbury,dist. h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. saguenay' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. lanaudiaire' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég bas-st laur,' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'richmond' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. gaspésie' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'edmonton zone' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'fraser south' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'chatham-kent u.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'wellington-duf-g' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'central zone' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. montérégie' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'yukon/northwest' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'south west nova' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'south zone' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'northwest' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'grey b. hlth un.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'southen health' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'vancouver' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'eastern ont. h.u' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'durham reg. hlth' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'city hamilton h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'haldimand-norf h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'renfrew county h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'pictou county' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. estrie' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. mauricie' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'v. district h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'kootenay-bound.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'heartland/pr. n' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'central vanc. is' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'waterloo h.u.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'prairie m. h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'capital district' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'kingston-front.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'city of toro. h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'north vanc. isl.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'peel region h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'western/labr' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'cape breton' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'calgary zone' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'halton reg. h. u' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'niagara reg. h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'leeds-grenville' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'north zone' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'central regio ha' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'zones 6 and 7' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'northen rha' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'northern inter.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'lambton h. unit' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'east kootenay' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. abitibi' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'saskatoon' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'huron, perth un.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'thunder bay d. h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'prince albert/ma' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'north bay h.u.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'northwestern h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'fraser north' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'simcoe musk. h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'zone 2' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'sun country/five' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'zone 3' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'brant count' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'zone 1' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'okanagan' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'porcupine h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'elgin-st thomas' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'north shore/c. g' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. des lauren.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'hastings. pie h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'middlesex-lon. h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'peterb. county h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'south vanc. isl' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. la chaud.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'windsor-essex h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'city of ottawa h' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'zones 4 and 5' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'winnipeg rha' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'oxford county h.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'dist. algoma' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'interlake e. rha' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'eastern regio ha' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'northeast' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'fraser east' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. cap. nat.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'thompson/carib.' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'regina qu''appe' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'rég. cote-nord' THEN at[2]
		WHEN lower(GEODPMF) LIKE 'don''t know' THEN null 
		WHEN lower(GEODPMF) LIKE 'n/s' THEN null 
		WHEN lower(GEODPMF) LIKE 'n/a' THEN null 
		WHEN lower(GEODPMF) LIKE 'refusal' THEN null 
		WHEN lower(GEODPMF) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEODPMF) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEODPMF) LIKE 'not required' THEN null 
	END AS GEODPMF,
	CASE
		WHEN lower(CIH_4) LIKE 'other' THEN at[2]
		WHEN lower(CIH_4) LIKE 'eating habits' THEN at[2]
		WHEN lower(CIH_4) LIKE 'more exercise' THEN at[2]
		WHEN lower(CIH_4) LIKE 'smoke less/stop' THEN at[2]
		WHEN lower(CIH_4) LIKE 'lost weight' THEN at[2]
		WHEN lower(CIH_4) LIKE 'medical treat' THEN at[2]
		WHEN lower(CIH_4) LIKE 'less alcohol' THEN at[2]
		WHEN lower(CIH_4) LIKE 'took vitamins' THEN at[2]
		WHEN lower(CIH_4) LIKE 'reduce stress le' THEN at[2]
		WHEN lower(CIH_4) LIKE 'don''t know' THEN null 
		WHEN lower(CIH_4) LIKE 'n/s' THEN null 
		WHEN lower(CIH_4) LIKE 'n/a' THEN null 
		WHEN lower(CIH_4) LIKE 'refusal' THEN null 
		WHEN lower(CIH_4) LIKE 'no drinks last w' THEN null 
		WHEN lower(CIH_4) LIKE 'no phy. activity' THEN null 
		WHEN lower(CIH_4) LIKE 'not required' THEN null 
	END AS CIH_4,
	CASE
		WHEN lower(PACDEE) LIKE '8.6' THEN 86
		WHEN lower(PACDEE) LIKE '8.7' THEN 87
		WHEN lower(PACDEE) LIKE '8.8' THEN 88
		WHEN lower(PACDEE) LIKE '8.9' THEN 89
		WHEN lower(PACDEE) LIKE '22.5' THEN 225
		WHEN lower(PACDEE) LIKE '22.4' THEN 224
		WHEN lower(PACDEE) LIKE '22.3' THEN 223
		WHEN lower(PACDEE) LIKE '22.2' THEN 222
		WHEN lower(PACDEE) LIKE '22.9' THEN 229
		WHEN lower(PACDEE) LIKE '22.7' THEN 227
		WHEN lower(PACDEE) LIKE '22.6' THEN 226
		WHEN lower(PACDEE) LIKE '10' THEN 100
		WHEN lower(PACDEE) LIKE '11' THEN 110
		WHEN lower(PACDEE) LIKE '12' THEN 120
		WHEN lower(PACDEE) LIKE '13' THEN 130
		WHEN lower(PACDEE) LIKE '14' THEN 140
		WHEN lower(PACDEE) LIKE '15' THEN 150
		WHEN lower(PACDEE) LIKE '18.9' THEN 189
		WHEN lower(PACDEE) LIKE '16' THEN 160
		WHEN lower(PACDEE) LIKE '18.8' THEN 188
		WHEN lower(PACDEE) LIKE '17' THEN 170
		WHEN lower(PACDEE) LIKE '18.7' THEN 187
		WHEN lower(PACDEE) LIKE '18' THEN 180
		WHEN lower(PACDEE) LIKE '10.6' THEN 106
		WHEN lower(PACDEE) LIKE '0' THEN 0
		WHEN lower(PACDEE) LIKE '10.5' THEN 105
		WHEN lower(PACDEE) LIKE '1' THEN 10
		WHEN lower(PACDEE) LIKE '10.4' THEN 104
		WHEN lower(PACDEE) LIKE '2' THEN 20
		WHEN lower(PACDEE) LIKE '10.3' THEN 103
		WHEN lower(PACDEE) LIKE '3' THEN 30
		WHEN lower(PACDEE) LIKE '10.2' THEN 102
		WHEN lower(PACDEE) LIKE '4' THEN 40
		WHEN lower(PACDEE) LIKE '10.1' THEN 101
		WHEN lower(PACDEE) LIKE '5' THEN 50
		WHEN lower(PACDEE) LIKE '6' THEN 60
		WHEN lower(PACDEE) LIKE '7' THEN 70
		WHEN lower(PACDEE) LIKE '18.6' THEN 186
		WHEN lower(PACDEE) LIKE '8' THEN 80
		WHEN lower(PACDEE) LIKE '18.5' THEN 185
		WHEN lower(PACDEE) LIKE '9' THEN 90
		WHEN lower(PACDEE) LIKE '18.4' THEN 184
		WHEN lower(PACDEE) LIKE '7.1' THEN 71
		WHEN lower(PACDEE) LIKE '18.3' THEN 183
		WHEN lower(PACDEE) LIKE '7.2' THEN 72
		WHEN lower(PACDEE) LIKE '18.2' THEN 182
		WHEN lower(PACDEE) LIKE '7.3' THEN 73
		WHEN lower(PACDEE) LIKE '18.1' THEN 181
		WHEN lower(PACDEE) LIKE '10.9' THEN 109
		WHEN lower(PACDEE) LIKE '7.4' THEN 74
		WHEN lower(PACDEE) LIKE '20' THEN 200
		WHEN lower(PACDEE) LIKE '10.8' THEN 108
		WHEN lower(PACDEE) LIKE '7.5' THEN 75
		WHEN lower(PACDEE) LIKE '21' THEN 210
		WHEN lower(PACDEE) LIKE '10.7' THEN 107
		WHEN lower(PACDEE) LIKE '7.6' THEN 76
		WHEN lower(PACDEE) LIKE '22' THEN 220
		WHEN lower(PACDEE) LIKE '7.7' THEN 77
		WHEN lower(PACDEE) LIKE '23' THEN 230
		WHEN lower(PACDEE) LIKE '7.8' THEN 78
		WHEN lower(PACDEE) LIKE '24' THEN 240
		WHEN lower(PACDEE) LIKE '7.9' THEN 79
		WHEN lower(PACDEE) LIKE '25' THEN 250
		WHEN lower(PACDEE) LIKE '26' THEN 260
		WHEN lower(PACDEE) LIKE '29.9' THEN 299
		WHEN lower(PACDEE) LIKE '29.7' THEN 297
		WHEN lower(PACDEE) LIKE '29' THEN 290
		WHEN lower(PACDEE) LIKE '21.6' THEN 216
		WHEN lower(PACDEE) LIKE '21.5' THEN 215
		WHEN lower(PACDEE) LIKE '21.4' THEN 214
		WHEN lower(PACDEE) LIKE '21.3' THEN 213
		WHEN lower(PACDEE) LIKE '21.2' THEN 212
		WHEN lower(PACDEE) LIKE '21.1' THEN 211
		WHEN lower(PACDEE) LIKE '29.3' THEN 293
		WHEN lower(PACDEE) LIKE '29.2' THEN 292
		WHEN lower(PACDEE) LIKE '30' THEN 300
		WHEN lower(PACDEE) LIKE '29.1' THEN 291
		WHEN lower(PACDEE) LIKE '21.8' THEN 218
		WHEN lower(PACDEE) LIKE '21.7' THEN 217
		WHEN lower(PACDEE) LIKE '17.9' THEN 179
		WHEN lower(PACDEE) LIKE '17.8' THEN 178
		WHEN lower(PACDEE) LIKE '17.7' THEN 177
		WHEN lower(PACDEE) LIKE '17.6' THEN 176
		WHEN lower(PACDEE) LIKE '6.1' THEN 61
		WHEN lower(PACDEE) LIKE '17.5' THEN 175
		WHEN lower(PACDEE) LIKE '6.2' THEN 62
		WHEN lower(PACDEE) LIKE '17.4' THEN 174
		WHEN lower(PACDEE) LIKE '6.3' THEN 63
		WHEN lower(PACDEE) LIKE '17.3' THEN 173
		WHEN lower(PACDEE) LIKE '6.4' THEN 64
		WHEN lower(PACDEE) LIKE '17.2' THEN 172
		WHEN lower(PACDEE) LIKE '6.5' THEN 65
		WHEN lower(PACDEE) LIKE '17.1' THEN 171
		WHEN lower(PACDEE) LIKE '6.6' THEN 66
		WHEN lower(PACDEE) LIKE '6.7' THEN 67
		WHEN lower(PACDEE) LIKE '6.8' THEN 68
		WHEN lower(PACDEE) LIKE '6.9' THEN 69
		WHEN lower(PACDEE) LIKE '20.7' THEN 207
		WHEN lower(PACDEE) LIKE '20.6' THEN 206
		WHEN lower(PACDEE) LIKE '20.5' THEN 205
		WHEN lower(PACDEE) LIKE '20.4' THEN 204
		WHEN lower(PACDEE) LIKE '20.3' THEN 203
		WHEN lower(PACDEE) LIKE '20.2' THEN 202
		WHEN lower(PACDEE) LIKE '20.1' THEN 201
		WHEN lower(PACDEE) LIKE '28.7' THEN 287
		WHEN lower(PACDEE) LIKE '28.6' THEN 286
		WHEN lower(PACDEE) LIKE '28.4' THEN 284
		WHEN lower(PACDEE) LIKE '20.9' THEN 209
		WHEN lower(PACDEE) LIKE '20.8' THEN 208
		WHEN lower(PACDEE) LIKE '16.9' THEN 169
		WHEN lower(PACDEE) LIKE '16.8' THEN 168
		WHEN lower(PACDEE) LIKE '5.1' THEN 51
		WHEN lower(PACDEE) LIKE '16.7' THEN 167
		WHEN lower(PACDEE) LIKE '5.2' THEN 52
		WHEN lower(PACDEE) LIKE '16.6' THEN 166
		WHEN lower(PACDEE) LIKE '5.3' THEN 53
		WHEN lower(PACDEE) LIKE '16.5' THEN 165
		WHEN lower(PACDEE) LIKE '5.4' THEN 54
		WHEN lower(PACDEE) LIKE '16.4' THEN 164
		WHEN lower(PACDEE) LIKE '5.5' THEN 55
		WHEN lower(PACDEE) LIKE '16.3' THEN 163
		WHEN lower(PACDEE) LIKE '5.6' THEN 56
		WHEN lower(PACDEE) LIKE '16.2' THEN 162
		WHEN lower(PACDEE) LIKE '5.7' THEN 57
		WHEN lower(PACDEE) LIKE '16.1' THEN 161
		WHEN lower(PACDEE) LIKE '5.8' THEN 58
		WHEN lower(PACDEE) LIKE '5.9' THEN 59
		WHEN lower(PACDEE) LIKE '27.7' THEN 277
		WHEN lower(PACDEE) LIKE '27.2' THEN 272
		WHEN lower(PACDEE) LIKE '27.1' THEN 271
		WHEN lower(PACDEE) LIKE '15.1' THEN 151
		WHEN lower(PACDEE) LIKE '30.1' THEN 301
		WHEN lower(PACDEE) LIKE '4.1' THEN 41
		WHEN lower(PACDEE) LIKE '15.9' THEN 159
		WHEN lower(PACDEE) LIKE '4.2' THEN 42
		WHEN lower(PACDEE) LIKE '15.8' THEN 158
		WHEN lower(PACDEE) LIKE '4.3' THEN 43
		WHEN lower(PACDEE) LIKE '15.7' THEN 157
		WHEN lower(PACDEE) LIKE '4.4' THEN 44
		WHEN lower(PACDEE) LIKE '15.6' THEN 156
		WHEN lower(PACDEE) LIKE '4.5' THEN 45
		WHEN lower(PACDEE) LIKE '15.5' THEN 155
		WHEN lower(PACDEE) LIKE '4.6' THEN 46
		WHEN lower(PACDEE) LIKE '15.4' THEN 154
		WHEN lower(PACDEE) LIKE '4.7' THEN 47
		WHEN lower(PACDEE) LIKE '15.3' THEN 153
		WHEN lower(PACDEE) LIKE '4.8' THEN 48
		WHEN lower(PACDEE) LIKE '30.9' THEN 309
		WHEN lower(PACDEE) LIKE '15.2' THEN 152
		WHEN lower(PACDEE) LIKE '4.9' THEN 49
		WHEN lower(PACDEE) LIKE '26.1' THEN 261
		WHEN lower(PACDEE) LIKE '26.9' THEN 269
		WHEN lower(PACDEE) LIKE '26.5' THEN 265
		WHEN lower(PACDEE) LIKE '26.3' THEN 263
		WHEN lower(PACDEE) LIKE '26.2' THEN 262
		WHEN lower(PACDEE) LIKE '14.2' THEN 142
		WHEN lower(PACDEE) LIKE '14.1' THEN 141
		WHEN lower(PACDEE) LIKE '3.1' THEN 31
		WHEN lower(PACDEE) LIKE '3.2' THEN 32
		WHEN lower(PACDEE) LIKE '3.3' THEN 33
		WHEN lower(PACDEE) LIKE '14.9' THEN 149
		WHEN lower(PACDEE) LIKE '3.4' THEN 34
		WHEN lower(PACDEE) LIKE '14.8' THEN 148
		WHEN lower(PACDEE) LIKE '3.5' THEN 35
		WHEN lower(PACDEE) LIKE '14.7' THEN 147
		WHEN lower(PACDEE) LIKE '3.6' THEN 36
		WHEN lower(PACDEE) LIKE '14.6' THEN 146
		WHEN lower(PACDEE) LIKE '3.7' THEN 37
		WHEN lower(PACDEE) LIKE '14.5' THEN 145
		WHEN lower(PACDEE) LIKE '3.8' THEN 38
		WHEN lower(PACDEE) LIKE '14.4' THEN 144
		WHEN lower(PACDEE) LIKE '3.9' THEN 39
		WHEN lower(PACDEE) LIKE '14.3' THEN 143
		WHEN lower(PACDEE) LIKE '25.2' THEN 252
		WHEN lower(PACDEE) LIKE '25.6' THEN 256
		WHEN lower(PACDEE) LIKE '25.5' THEN 255
		WHEN lower(PACDEE) LIKE '25.3' THEN 253
		WHEN lower(PACDEE) LIKE '13.3' THEN 133
		WHEN lower(PACDEE) LIKE '13.2' THEN 132
		WHEN lower(PACDEE) LIKE '13.1' THEN 131
		WHEN lower(PACDEE) LIKE '2.1' THEN 21
		WHEN lower(PACDEE) LIKE '2.2' THEN 22
		WHEN lower(PACDEE) LIKE '2.3' THEN 23
		WHEN lower(PACDEE) LIKE '2.4' THEN 24
		WHEN lower(PACDEE) LIKE '2.5' THEN 25
		WHEN lower(PACDEE) LIKE '13.9' THEN 139
		WHEN lower(PACDEE) LIKE '2.6' THEN 26
		WHEN lower(PACDEE) LIKE '13.8' THEN 138
		WHEN lower(PACDEE) LIKE '2.7' THEN 27
		WHEN lower(PACDEE) LIKE '13.7' THEN 137
		WHEN lower(PACDEE) LIKE '2.8' THEN 28
		WHEN lower(PACDEE) LIKE '36.5' THEN 365
		WHEN lower(PACDEE) LIKE '13.6' THEN 136
		WHEN lower(PACDEE) LIKE '2.9' THEN 29
		WHEN lower(PACDEE) LIKE '13.5' THEN 135
		WHEN lower(PACDEE) LIKE '13.4' THEN 134
		WHEN lower(PACDEE) LIKE '24.3' THEN 243
		WHEN lower(PACDEE) LIKE '24.2' THEN 242
		WHEN lower(PACDEE) LIKE '24.1' THEN 241
		WHEN lower(PACDEE) LIKE '24.9' THEN 249
		WHEN lower(PACDEE) LIKE '24.7' THEN 247
		WHEN lower(PACDEE) LIKE '12.4' THEN 124
		WHEN lower(PACDEE) LIKE '12.3' THEN 123
		WHEN lower(PACDEE) LIKE '12.2' THEN 122
		WHEN lower(PACDEE) LIKE '12.1' THEN 121
		WHEN lower(PACDEE) LIKE '1.1' THEN 11
		WHEN lower(PACDEE) LIKE '1.2' THEN 12
		WHEN lower(PACDEE) LIKE '1.3' THEN 13
		WHEN lower(PACDEE) LIKE '1.4' THEN 14
		WHEN lower(PACDEE) LIKE '1.5' THEN 15
		WHEN lower(PACDEE) LIKE '1.6' THEN 16
		WHEN lower(PACDEE) LIKE '1.7' THEN 17
		WHEN lower(PACDEE) LIKE '12.9' THEN 129
		WHEN lower(PACDEE) LIKE '1.8' THEN 18
		WHEN lower(PACDEE) LIKE '35.7' THEN 357
		WHEN lower(PACDEE) LIKE '12.8' THEN 128
		WHEN lower(PACDEE) LIKE '9.1' THEN 91
		WHEN lower(PACDEE) LIKE '1.9' THEN 19
		WHEN lower(PACDEE) LIKE '12.7' THEN 127
		WHEN lower(PACDEE) LIKE '9.2' THEN 92
		WHEN lower(PACDEE) LIKE '12.6' THEN 126
		WHEN lower(PACDEE) LIKE '9.3' THEN 93
		WHEN lower(PACDEE) LIKE '12.5' THEN 125
		WHEN lower(PACDEE) LIKE '9.4' THEN 94
		WHEN lower(PACDEE) LIKE '9.5' THEN 95
		WHEN lower(PACDEE) LIKE '9.6' THEN 96
		WHEN lower(PACDEE) LIKE '9.7' THEN 97
		WHEN lower(PACDEE) LIKE '9.8' THEN 98
		WHEN lower(PACDEE) LIKE '9.9' THEN 99
		WHEN lower(PACDEE) LIKE '23.3' THEN 233
		WHEN lower(PACDEE) LIKE '23.2' THEN 232
		WHEN lower(PACDEE) LIKE '23.1' THEN 231
		WHEN lower(PACDEE) LIKE '23.9' THEN 239
		WHEN lower(PACDEE) LIKE '23.8' THEN 238
		WHEN lower(PACDEE) LIKE '23.7' THEN 237
		WHEN lower(PACDEE) LIKE '23.6' THEN 236
		WHEN lower(PACDEE) LIKE '19.9' THEN 199
		WHEN lower(PACDEE) LIKE '19.8' THEN 198
		WHEN lower(PACDEE) LIKE '19.7' THEN 197
		WHEN lower(PACDEE) LIKE '19.6' THEN 196
		WHEN lower(PACDEE) LIKE '11.5' THEN 115
		WHEN lower(PACDEE) LIKE '11.4' THEN 114
		WHEN lower(PACDEE) LIKE '11.3' THEN 113
		WHEN lower(PACDEE) LIKE '11.2' THEN 112
		WHEN lower(PACDEE) LIKE '0.1' THEN 1
		WHEN lower(PACDEE) LIKE '11.1' THEN 111
		WHEN lower(PACDEE) LIKE '0.2' THEN 2
		WHEN lower(PACDEE) LIKE '0.3' THEN 3
		WHEN lower(PACDEE) LIKE '0.4' THEN 4
		WHEN lower(PACDEE) LIKE '0.5' THEN 5
		WHEN lower(PACDEE) LIKE '19.5' THEN 195
		WHEN lower(PACDEE) LIKE '0.6' THEN 6
		WHEN lower(PACDEE) LIKE '19.4' THEN 194
		WHEN lower(PACDEE) LIKE '0.7' THEN 7
		WHEN lower(PACDEE) LIKE '19.3' THEN 193
		WHEN lower(PACDEE) LIKE '0.8' THEN 8
		WHEN lower(PACDEE) LIKE '19.2' THEN 192
		WHEN lower(PACDEE) LIKE '8.1' THEN 81
		WHEN lower(PACDEE) LIKE '0.9' THEN 9
		WHEN lower(PACDEE) LIKE '19.1' THEN 191
		WHEN lower(PACDEE) LIKE '11.9' THEN 119
		WHEN lower(PACDEE) LIKE '8.2' THEN 82
		WHEN lower(PACDEE) LIKE '11.8' THEN 118
		WHEN lower(PACDEE) LIKE '8.3' THEN 83
		WHEN lower(PACDEE) LIKE '11.7' THEN 117
		WHEN lower(PACDEE) LIKE '8.4' THEN 84
		WHEN lower(PACDEE) LIKE '11.6' THEN 116
		WHEN lower(PACDEE) LIKE '8.5' THEN 85
		WHEN lower(PACDEE) LIKE 'don''t know' THEN null 
		WHEN lower(PACDEE) LIKE 'n/s' THEN null 
		WHEN lower(PACDEE) LIKE 'n/a' THEN null 
		WHEN lower(PACDEE) LIKE 'refusal' THEN null 
		WHEN lower(PACDEE) LIKE 'no drinks last w' THEN null 
		WHEN lower(PACDEE) LIKE 'no phy. activity' THEN null 
		WHEN lower(PACDEE) LIKE 'not required' THEN null 
	END AS PACDEE,
	CASE
		WHEN lower(DHHGLVG) LIKE 'parent child' THEN at[2]
		WHEN lower(DHHGLVG) LIKE 'child parent sib' THEN at[2]
		WHEN lower(DHHGLVG) LIKE 'other' THEN at[2]
		WHEN lower(DHHGLVG) LIKE 'unattached oth' THEN at[2]
		WHEN lower(DHHGLVG) LIKE 'unattached alone' THEN at[2]
		WHEN lower(DHHGLVG) LIKE 'child 2 par sib' THEN at[2]
		WHEN lower(DHHGLVG) LIKE 'spouse / parter' THEN at[2]
		WHEN lower(DHHGLVG) LIKE 'par spouse child' THEN at[2]
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
		WHEN lower(CIH_1) LIKE 'yes' THEN 1
		WHEN lower(CIH_1) LIKE 'don''t know' THEN null 
		WHEN lower(CIH_1) LIKE 'n/s' THEN null 
		WHEN lower(CIH_1) LIKE 'n/a' THEN null 
		WHEN lower(CIH_1) LIKE 'refusal' THEN null 
		WHEN lower(CIH_1) LIKE 'no drinks last w' THEN null 
		WHEN lower(CIH_1) LIKE 'no phy. activity' THEN null 
		WHEN lower(CIH_1) LIKE 'not required' THEN null 
	END AS CIH_1,
	CASE
		WHEN lower(HUPDPAD) LIKE 'prevents most' THEN at[2]
		WHEN lower(HUPDPAD) LIKE 'prevents some' THEN at[2]
		WHEN lower(HUPDPAD) LIKE 'no pain' THEN at[2]
		WHEN lower(HUPDPAD) LIKE 'prevents a few' THEN at[2]
		WHEN lower(HUPDPAD) LIKE 'doesn''t prevent' THEN at[2]
		WHEN lower(HUPDPAD) LIKE 'don''t know' THEN null 
		WHEN lower(HUPDPAD) LIKE 'n/s' THEN null 
		WHEN lower(HUPDPAD) LIKE 'n/a' THEN null 
		WHEN lower(HUPDPAD) LIKE 'refusal' THEN null 
		WHEN lower(HUPDPAD) LIKE 'no drinks last w' THEN null 
		WHEN lower(HUPDPAD) LIKE 'no phy. activity' THEN null 
		WHEN lower(HUPDPAD) LIKE 'not required' THEN null 
	END AS HUPDPAD,
	CASE
		WHEN lower(CIH_2) LIKE 'other' THEN at[2]
		WHEN lower(CIH_2) LIKE 'eating habits' THEN at[2]
		WHEN lower(CIH_2) LIKE 'more exercise' THEN at[2]
		WHEN lower(CIH_2) LIKE 'smoke less/stop' THEN at[2]
		WHEN lower(CIH_2) LIKE 'lost weight' THEN at[2]
		WHEN lower(CIH_2) LIKE 'medical treat' THEN at[2]
		WHEN lower(CIH_2) LIKE 'less alcohol' THEN at[2]
		WHEN lower(CIH_2) LIKE 'took vitamins' THEN at[2]
		WHEN lower(CIH_2) LIKE 'reduce stress le' THEN at[2]
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
		WHEN lower(SPSDATT) LIKE '3' THEN 3
		WHEN lower(SPSDATT) LIKE '4' THEN 4
		WHEN lower(SPSDATT) LIKE '5' THEN 5
		WHEN lower(SPSDATT) LIKE '6' THEN 6
		WHEN lower(SPSDATT) LIKE '7' THEN 7
		WHEN lower(SPSDATT) LIKE '8' THEN 8
		WHEN lower(SPSDATT) LIKE '9' THEN 9
		WHEN lower(SPSDATT) LIKE 'don''t know' THEN null 
		WHEN lower(SPSDATT) LIKE 'n/s' THEN null 
		WHEN lower(SPSDATT) LIKE 'n/a' THEN null 
		WHEN lower(SPSDATT) LIKE 'refusal' THEN null 
		WHEN lower(SPSDATT) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPSDATT) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPSDATT) LIKE 'not required' THEN null 
	END AS SPSDATT,
	CASE
		WHEN lower(GEOGPRV) LIKE 'yukon/nwt/nuna.' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'pei' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'ontario' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'manitoba' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'saskatchewan' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'nova scotia' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'new brunswick' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'british columbia' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'nfld & lab.' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'quebec' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'alberta' THEN at[2]
		WHEN lower(GEOGPRV) LIKE 'don''t know' THEN null 
		WHEN lower(GEOGPRV) LIKE 'n/s' THEN null 
		WHEN lower(GEOGPRV) LIKE 'n/a' THEN null 
		WHEN lower(GEOGPRV) LIKE 'refusal' THEN null 
		WHEN lower(GEOGPRV) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEOGPRV) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEOGPRV) LIKE 'not required' THEN null 
	END AS GEOGPRV,
	CASE
		WHEN lower(CHPG04) LIKE '22' THEN 22
		WHEN lower(CHPG04) LIKE '23' THEN 23
		WHEN lower(CHPG04) LIKE '24' THEN 24
		WHEN lower(CHPG04) LIKE '25' THEN 25
		WHEN lower(CHPG04) LIKE '26' THEN 26
		WHEN lower(CHPG04) LIKE '27' THEN 27
		WHEN lower(CHPG04) LIKE '28' THEN 28
		WHEN lower(CHPG04) LIKE '29' THEN 29
		WHEN lower(CHPG04) LIKE '30' THEN 30
		WHEN lower(CHPG04) LIKE '31+ consultation' THEN 31
		WHEN lower(CHPG04) LIKE '10' THEN 10
		WHEN lower(CHPG04) LIKE '11' THEN 11
		WHEN lower(CHPG04) LIKE '12' THEN 12
		WHEN lower(CHPG04) LIKE '13' THEN 13
		WHEN lower(CHPG04) LIKE '14' THEN 14
		WHEN lower(CHPG04) LIKE '15' THEN 15
		WHEN lower(CHPG04) LIKE '16' THEN 16
		WHEN lower(CHPG04) LIKE '17' THEN 17
		WHEN lower(CHPG04) LIKE '18' THEN 18
		WHEN lower(CHPG04) LIKE '19' THEN 19
		WHEN lower(CHPG04) LIKE '0' THEN 0
		WHEN lower(CHPG04) LIKE '1' THEN 1
		WHEN lower(CHPG04) LIKE '2' THEN 2
		WHEN lower(CHPG04) LIKE '3' THEN 3
		WHEN lower(CHPG04) LIKE '4' THEN 4
		WHEN lower(CHPG04) LIKE '5' THEN 5
		WHEN lower(CHPG04) LIKE '6' THEN 6
		WHEN lower(CHPG04) LIKE '7' THEN 7
		WHEN lower(CHPG04) LIKE '8' THEN 8
		WHEN lower(CHPG04) LIKE '9' THEN 9
		WHEN lower(CHPG04) LIKE '20' THEN 20
		WHEN lower(CHPG04) LIKE '21' THEN 21
		WHEN lower(CHPG04) LIKE 'don''t know' THEN null 
		WHEN lower(CHPG04) LIKE 'n/s' THEN null 
		WHEN lower(CHPG04) LIKE 'n/a' THEN null 
		WHEN lower(CHPG04) LIKE 'refusal' THEN null 
		WHEN lower(CHPG04) LIKE 'no drinks last w' THEN null 
		WHEN lower(CHPG04) LIKE 'no phy. activity' THEN null 
		WHEN lower(CHPG04) LIKE 'not required' THEN null 
	END AS CHPG04,
	CASE
		WHEN lower(SPSDWOR) LIKE '2' THEN 2
		WHEN lower(SPSDWOR) LIKE '3' THEN 3
		WHEN lower(SPSDWOR) LIKE '4' THEN 4
		WHEN lower(SPSDWOR) LIKE '5' THEN 5
		WHEN lower(SPSDWOR) LIKE '6' THEN 6
		WHEN lower(SPSDWOR) LIKE '7' THEN 7
		WHEN lower(SPSDWOR) LIKE '8' THEN 8
		WHEN lower(SPSDWOR) LIKE 'don''t know' THEN null 
		WHEN lower(SPSDWOR) LIKE 'n/s' THEN null 
		WHEN lower(SPSDWOR) LIKE 'n/a' THEN null 
		WHEN lower(SPSDWOR) LIKE 'refusal' THEN null 
		WHEN lower(SPSDWOR) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPSDWOR) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPSDWOR) LIKE 'not required' THEN null 
	END AS SPSDWOR,
	CASE
		WHEN lower(GEN_10) LIKE 'very strong' THEN 4
		WHEN lower(GEN_10) LIKE 'somewhat strong' THEN 3
		WHEN lower(GEN_10) LIKE 'very weak' THEN 2
		WHEN lower(GEN_10) LIKE 'somewhat weak' THEN 1
		WHEN lower(GEN_10) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_10) LIKE 'n/s' THEN null 
		WHEN lower(GEN_10) LIKE 'n/a' THEN null 
		WHEN lower(GEN_10) LIKE 'refusal' THEN null 
		WHEN lower(GEN_10) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_10) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_10) LIKE 'not required' THEN null 
	END AS GEN_10,
	CASE
		WHEN lower(CHPGMDC) LIKE '22' THEN 22
		WHEN lower(CHPGMDC) LIKE '23' THEN 23
		WHEN lower(CHPGMDC) LIKE '24' THEN 24
		WHEN lower(CHPGMDC) LIKE '25' THEN 25
		WHEN lower(CHPGMDC) LIKE '26' THEN 26
		WHEN lower(CHPGMDC) LIKE '27' THEN 27
		WHEN lower(CHPGMDC) LIKE '28' THEN 28
		WHEN lower(CHPGMDC) LIKE '29' THEN 29
		WHEN lower(CHPGMDC) LIKE '30' THEN 30
		WHEN lower(CHPGMDC) LIKE '31+ consultation' THEN 31
		WHEN lower(CHPGMDC) LIKE '10' THEN 10
		WHEN lower(CHPGMDC) LIKE '11' THEN 11
		WHEN lower(CHPGMDC) LIKE '12' THEN 12
		WHEN lower(CHPGMDC) LIKE '13' THEN 13
		WHEN lower(CHPGMDC) LIKE '14' THEN 14
		WHEN lower(CHPGMDC) LIKE '15' THEN 15
		WHEN lower(CHPGMDC) LIKE '16' THEN 16
		WHEN lower(CHPGMDC) LIKE '17' THEN 17
		WHEN lower(CHPGMDC) LIKE '18' THEN 18
		WHEN lower(CHPGMDC) LIKE '19' THEN 19
		WHEN lower(CHPGMDC) LIKE '0' THEN 0
		WHEN lower(CHPGMDC) LIKE '1' THEN 1
		WHEN lower(CHPGMDC) LIKE '2' THEN 2
		WHEN lower(CHPGMDC) LIKE '3' THEN 3
		WHEN lower(CHPGMDC) LIKE '4' THEN 4
		WHEN lower(CHPGMDC) LIKE '5' THEN 5
		WHEN lower(CHPGMDC) LIKE '6' THEN 6
		WHEN lower(CHPGMDC) LIKE '7' THEN 7
		WHEN lower(CHPGMDC) LIKE '8' THEN 8
		WHEN lower(CHPGMDC) LIKE '9' THEN 9
		WHEN lower(CHPGMDC) LIKE '20' THEN 20
		WHEN lower(CHPGMDC) LIKE '21' THEN 21
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
		WHEN lower(SFE_504) LIKE 'strongly agree' THEN 4
		WHEN lower(SFE_504) LIKE 'neither nor' THEN 2
		WHEN lower(SFE_504) LIKE 'disagree' THEN 1
		WHEN lower(SFE_504) LIKE 'agree' THEN 3
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
		WHEN lower(DISDCHR) LIKE 'somewhat more' THEN 6
		WHEN lower(DISDCHR) LIKE 'little more' THEN 5
		WHEN lower(DISDCHR) LIKE 'somewhat less' THEN 3
		WHEN lower(DISDCHR) LIKE 'a lot less' THEN 1
		WHEN lower(DISDCHR) LIKE 'about the same' THEN 4
		WHEN lower(DISDCHR) LIKE 'a lot more' THEN 7
		WHEN lower(DISDCHR) LIKE 'don''t know' THEN null 
		WHEN lower(DISDCHR) LIKE 'n/s' THEN null 
		WHEN lower(DISDCHR) LIKE 'n/a' THEN null 
		WHEN lower(DISDCHR) LIKE 'refusal' THEN null 
		WHEN lower(DISDCHR) LIKE 'no drinks last w' THEN null 
		WHEN lower(DISDCHR) LIKE 'no phy. activity' THEN null 
		WHEN lower(DISDCHR) LIKE 'not required' THEN null 
	END AS DISDCHR,
	CASE
		WHEN lower(DHHGAGE) LIKE '18 to 19 years' THEN 18
		WHEN lower(DHHGAGE) LIKE '75 to 79 years' THEN 75
		WHEN lower(DHHGAGE) LIKE '55 to 59 years' THEN 55
		WHEN lower(DHHGAGE) LIKE '65 to 69 years' THEN 65
		WHEN lower(DHHGAGE) LIKE '12 to 14 years' THEN 12
		WHEN lower(DHHGAGE) LIKE '20 to 24 years' THEN 20
		WHEN lower(DHHGAGE) LIKE '15 to 17 years' THEN 15
		WHEN lower(DHHGAGE) LIKE '30 to 34 years' THEN 30
		WHEN lower(DHHGAGE) LIKE '50 to 54 years' THEN 50
		WHEN lower(DHHGAGE) LIKE '40 to 44 years' THEN 40
		WHEN lower(DHHGAGE) LIKE '70 to 74 years' THEN 70
		WHEN lower(DHHGAGE) LIKE '25 to 29 years' THEN 25
		WHEN lower(DHHGAGE) LIKE '80 years or more' THEN 80
		WHEN lower(DHHGAGE) LIKE '45 to 49 years' THEN 45
		WHEN lower(DHHGAGE) LIKE '35 to 39 years' THEN 35
		WHEN lower(DHHGAGE) LIKE '60 to 64 years' THEN 60
		WHEN lower(DHHGAGE) LIKE 'don''t know' THEN null 
		WHEN lower(DHHGAGE) LIKE 'n/s' THEN null 
		WHEN lower(DHHGAGE) LIKE 'n/a' THEN null 
		WHEN lower(DHHGAGE) LIKE 'refusal' THEN null 
		WHEN lower(DHHGAGE) LIKE 'no drinks last w' THEN null 
		WHEN lower(DHHGAGE) LIKE 'no phy. activity' THEN null 
		WHEN lower(DHHGAGE) LIKE 'not required' THEN null 
	END AS DHHGAGE,
	CASE
		WHEN lower(GEN_09) LIKE 'quite a bit' THEN 3
		WHEN lower(GEN_09) LIKE 'not at all' THEN 0
		WHEN lower(GEN_09) LIKE 'not very' THEN 2
		WHEN lower(GEN_09) LIKE 'extremely' THEN 4
		WHEN lower(GEN_09) LIKE 'a bit' THEN 1
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
		WHEN lower(ACC_40) LIKE 'yes' THEN 1
		WHEN lower(ACC_40) LIKE 'don''t know' THEN null 
		WHEN lower(ACC_40) LIKE 'n/s' THEN null 
		WHEN lower(ACC_40) LIKE 'n/a' THEN null 
		WHEN lower(ACC_40) LIKE 'refusal' THEN null 
		WHEN lower(ACC_40) LIKE 'no drinks last w' THEN null 
		WHEN lower(ACC_40) LIKE 'no phy. activity' THEN null 
		WHEN lower(ACC_40) LIKE 'not required' THEN null 
	END AS ACC_40,
	CASE
		WHEN lower(SFEDE1) LIKE '22' THEN 22
		WHEN lower(SFEDE1) LIKE '23' THEN 23
		WHEN lower(SFEDE1) LIKE '24' THEN 24
		WHEN lower(SFEDE1) LIKE '25' THEN 25
		WHEN lower(SFEDE1) LIKE '26' THEN 26
		WHEN lower(SFEDE1) LIKE '27' THEN 27
		WHEN lower(SFEDE1) LIKE '28' THEN 28
		WHEN lower(SFEDE1) LIKE '29' THEN 29
		WHEN lower(SFEDE1) LIKE '30' THEN 30
		WHEN lower(SFEDE1) LIKE '31' THEN 31
		WHEN lower(SFEDE1) LIKE '10' THEN 10
		WHEN lower(SFEDE1) LIKE '32' THEN 32
		WHEN lower(SFEDE1) LIKE '11' THEN 11
		WHEN lower(SFEDE1) LIKE '12' THEN 12
		WHEN lower(SFEDE1) LIKE '13' THEN 13
		WHEN lower(SFEDE1) LIKE '14' THEN 14
		WHEN lower(SFEDE1) LIKE '15' THEN 15
		WHEN lower(SFEDE1) LIKE '16' THEN 16
		WHEN lower(SFEDE1) LIKE '17' THEN 17
		WHEN lower(SFEDE1) LIKE '18' THEN 18
		WHEN lower(SFEDE1) LIKE '19' THEN 19
		WHEN lower(SFEDE1) LIKE '0' THEN 0
		WHEN lower(SFEDE1) LIKE '1' THEN 1
		WHEN lower(SFEDE1) LIKE '2' THEN 2
		WHEN lower(SFEDE1) LIKE '3' THEN 3
		WHEN lower(SFEDE1) LIKE '4' THEN 4
		WHEN lower(SFEDE1) LIKE '5' THEN 5
		WHEN lower(SFEDE1) LIKE '6' THEN 6
		WHEN lower(SFEDE1) LIKE '7' THEN 7
		WHEN lower(SFEDE1) LIKE '8' THEN 8
		WHEN lower(SFEDE1) LIKE '9' THEN 9
		WHEN lower(SFEDE1) LIKE '20' THEN 20
		WHEN lower(SFEDE1) LIKE '21' THEN 21
		WHEN lower(SFEDE1) LIKE 'don''t know' THEN null 
		WHEN lower(SFEDE1) LIKE 'n/s' THEN null 
		WHEN lower(SFEDE1) LIKE 'n/a' THEN null 
		WHEN lower(SFEDE1) LIKE 'refusal' THEN null 
		WHEN lower(SFEDE1) LIKE 'no drinks last w' THEN null 
		WHEN lower(SFEDE1) LIKE 'no phy. activity' THEN null 
		WHEN lower(SFEDE1) LIKE 'not required' THEN null 
	END AS SFEDE1,
	CASE
		WHEN lower(SPV_6) LIKE 'not at all' THEN at[2]
		WHEN lower(SPV_6) LIKE 'once/month' THEN at[2]
		WHEN lower(SPV_6) LIKE 'once/year' THEN at[2]
		WHEN lower(SPV_6) LIKE 'once/week or +' THEN at[2]
		WHEN lower(SPV_6) LIKE '3 to 4 times/yr' THEN at[2]
		WHEN lower(SPV_6) LIKE 'don''t know' THEN null 
		WHEN lower(SPV_6) LIKE 'n/s' THEN null 
		WHEN lower(SPV_6) LIKE 'n/a' THEN null 
		WHEN lower(SPV_6) LIKE 'refusal' THEN null 
		WHEN lower(SPV_6) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPV_6) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPV_6) LIKE 'not required' THEN null 
	END AS SPV_6,
	CASE
		WHEN lower(FVCDTOT) LIKE '8.6' THEN 86
		WHEN lower(FVCDTOT) LIKE '8.7' THEN 87
		WHEN lower(FVCDTOT) LIKE '8.8' THEN 88
		WHEN lower(FVCDTOT) LIKE '8.9' THEN 89
		WHEN lower(FVCDTOT) LIKE '22.5' THEN 225
		WHEN lower(FVCDTOT) LIKE '22.4' THEN 224
		WHEN lower(FVCDTOT) LIKE '22.3' THEN 223
		WHEN lower(FVCDTOT) LIKE '22.2' THEN 222
		WHEN lower(FVCDTOT) LIKE '22.1' THEN 221
		WHEN lower(FVCDTOT) LIKE '22.6' THEN 226
		WHEN lower(FVCDTOT) LIKE '10' THEN 100
		WHEN lower(FVCDTOT) LIKE '11' THEN 110
		WHEN lower(FVCDTOT) LIKE '12' THEN 120
		WHEN lower(FVCDTOT) LIKE '13' THEN 130
		WHEN lower(FVCDTOT) LIKE '14' THEN 140
		WHEN lower(FVCDTOT) LIKE '15' THEN 150
		WHEN lower(FVCDTOT) LIKE '16' THEN 160
		WHEN lower(FVCDTOT) LIKE '18.8' THEN 188
		WHEN lower(FVCDTOT) LIKE '17' THEN 170
		WHEN lower(FVCDTOT) LIKE '18.7' THEN 187
		WHEN lower(FVCDTOT) LIKE '18' THEN 180
		WHEN lower(FVCDTOT) LIKE '19' THEN 190
		WHEN lower(FVCDTOT) LIKE '10.6' THEN 106
		WHEN lower(FVCDTOT) LIKE '0' THEN 0
		WHEN lower(FVCDTOT) LIKE '10.5' THEN 105
		WHEN lower(FVCDTOT) LIKE '1' THEN 10
		WHEN lower(FVCDTOT) LIKE '10.4' THEN 104
		WHEN lower(FVCDTOT) LIKE '2' THEN 20
		WHEN lower(FVCDTOT) LIKE '10.3' THEN 103
		WHEN lower(FVCDTOT) LIKE '3' THEN 30
		WHEN lower(FVCDTOT) LIKE '10.2' THEN 102
		WHEN lower(FVCDTOT) LIKE '4' THEN 40
		WHEN lower(FVCDTOT) LIKE '10.1' THEN 101
		WHEN lower(FVCDTOT) LIKE '5' THEN 50
		WHEN lower(FVCDTOT) LIKE '6' THEN 60
		WHEN lower(FVCDTOT) LIKE '7' THEN 70
		WHEN lower(FVCDTOT) LIKE '18.6' THEN 186
		WHEN lower(FVCDTOT) LIKE '8' THEN 80
		WHEN lower(FVCDTOT) LIKE '18.5' THEN 185
		WHEN lower(FVCDTOT) LIKE '9' THEN 90
		WHEN lower(FVCDTOT) LIKE '18.4' THEN 184
		WHEN lower(FVCDTOT) LIKE '7.1' THEN 71
		WHEN lower(FVCDTOT) LIKE '18.3' THEN 183
		WHEN lower(FVCDTOT) LIKE '7.2' THEN 72
		WHEN lower(FVCDTOT) LIKE '18.2' THEN 182
		WHEN lower(FVCDTOT) LIKE '7.3' THEN 73
		WHEN lower(FVCDTOT) LIKE '18.1' THEN 181
		WHEN lower(FVCDTOT) LIKE '10.9' THEN 109
		WHEN lower(FVCDTOT) LIKE '7.4' THEN 74
		WHEN lower(FVCDTOT) LIKE '20' THEN 200
		WHEN lower(FVCDTOT) LIKE '10.8' THEN 108
		WHEN lower(FVCDTOT) LIKE '7.5' THEN 75
		WHEN lower(FVCDTOT) LIKE '33.6' THEN 336
		WHEN lower(FVCDTOT) LIKE '21' THEN 210
		WHEN lower(FVCDTOT) LIKE '10.7' THEN 107
		WHEN lower(FVCDTOT) LIKE '7.6' THEN 76
		WHEN lower(FVCDTOT) LIKE '22' THEN 220
		WHEN lower(FVCDTOT) LIKE '7.7' THEN 77
		WHEN lower(FVCDTOT) LIKE '23' THEN 230
		WHEN lower(FVCDTOT) LIKE '7.8' THEN 78
		WHEN lower(FVCDTOT) LIKE '7.9' THEN 79
		WHEN lower(FVCDTOT) LIKE '25' THEN 250
		WHEN lower(FVCDTOT) LIKE '29' THEN 290
		WHEN lower(FVCDTOT) LIKE '21.6' THEN 216
		WHEN lower(FVCDTOT) LIKE '21.4' THEN 214
		WHEN lower(FVCDTOT) LIKE '21.3' THEN 213
		WHEN lower(FVCDTOT) LIKE '21.2' THEN 212
		WHEN lower(FVCDTOT) LIKE '21.1' THEN 211
		WHEN lower(FVCDTOT) LIKE '29.6' THEN 296
		WHEN lower(FVCDTOT) LIKE '29.3' THEN 293
		WHEN lower(FVCDTOT) LIKE '34' THEN 340
		WHEN lower(FVCDTOT) LIKE '17.9' THEN 179
		WHEN lower(FVCDTOT) LIKE '17.8' THEN 178
		WHEN lower(FVCDTOT) LIKE '32.3' THEN 323
		WHEN lower(FVCDTOT) LIKE '17.7' THEN 177
		WHEN lower(FVCDTOT) LIKE '17.6' THEN 176
		WHEN lower(FVCDTOT) LIKE '6.1' THEN 61
		WHEN lower(FVCDTOT) LIKE '17.5' THEN 175
		WHEN lower(FVCDTOT) LIKE '6.2' THEN 62
		WHEN lower(FVCDTOT) LIKE '17.4' THEN 174
		WHEN lower(FVCDTOT) LIKE '6.3' THEN 63
		WHEN lower(FVCDTOT) LIKE '17.3' THEN 173
		WHEN lower(FVCDTOT) LIKE '6.4' THEN 64
		WHEN lower(FVCDTOT) LIKE '17.2' THEN 172
		WHEN lower(FVCDTOT) LIKE '6.5' THEN 65
		WHEN lower(FVCDTOT) LIKE '17.1' THEN 171
		WHEN lower(FVCDTOT) LIKE '6.6' THEN 66
		WHEN lower(FVCDTOT) LIKE '6.7' THEN 67
		WHEN lower(FVCDTOT) LIKE '6.8' THEN 68
		WHEN lower(FVCDTOT) LIKE '6.9' THEN 69
		WHEN lower(FVCDTOT) LIKE '20.7' THEN 207
		WHEN lower(FVCDTOT) LIKE '20.6' THEN 206
		WHEN lower(FVCDTOT) LIKE '20.4' THEN 204
		WHEN lower(FVCDTOT) LIKE '20.3' THEN 203
		WHEN lower(FVCDTOT) LIKE '20.1' THEN 201
		WHEN lower(FVCDTOT) LIKE '20.9' THEN 209
		WHEN lower(FVCDTOT) LIKE '28.1' THEN 281
		WHEN lower(FVCDTOT) LIKE '20.8' THEN 208
		WHEN lower(FVCDTOT) LIKE '16.9' THEN 169
		WHEN lower(FVCDTOT) LIKE '31.6' THEN 316
		WHEN lower(FVCDTOT) LIKE '31.1' THEN 311
		WHEN lower(FVCDTOT) LIKE '16.8' THEN 168
		WHEN lower(FVCDTOT) LIKE '5.1' THEN 51
		WHEN lower(FVCDTOT) LIKE '16.7' THEN 167
		WHEN lower(FVCDTOT) LIKE '5.2' THEN 52
		WHEN lower(FVCDTOT) LIKE '16.6' THEN 166
		WHEN lower(FVCDTOT) LIKE '5.3' THEN 53
		WHEN lower(FVCDTOT) LIKE '16.5' THEN 165
		WHEN lower(FVCDTOT) LIKE '5.4' THEN 54
		WHEN lower(FVCDTOT) LIKE '16.4' THEN 164
		WHEN lower(FVCDTOT) LIKE '5.5' THEN 55
		WHEN lower(FVCDTOT) LIKE '16.3' THEN 163
		WHEN lower(FVCDTOT) LIKE '5.6' THEN 56
		WHEN lower(FVCDTOT) LIKE '16.2' THEN 162
		WHEN lower(FVCDTOT) LIKE '5.7' THEN 57
		WHEN lower(FVCDTOT) LIKE '16.1' THEN 161
		WHEN lower(FVCDTOT) LIKE '5.8' THEN 58
		WHEN lower(FVCDTOT) LIKE '5.9' THEN 59
		WHEN lower(FVCDTOT) LIKE '27.9' THEN 279
		WHEN lower(FVCDTOT) LIKE '15.1' THEN 151
		WHEN lower(FVCDTOT) LIKE '4.1' THEN 41
		WHEN lower(FVCDTOT) LIKE '15.9' THEN 159
		WHEN lower(FVCDTOT) LIKE '4.2' THEN 42
		WHEN lower(FVCDTOT) LIKE '15.8' THEN 158
		WHEN lower(FVCDTOT) LIKE '4.3' THEN 43
		WHEN lower(FVCDTOT) LIKE '15.7' THEN 157
		WHEN lower(FVCDTOT) LIKE '4.4' THEN 44
		WHEN lower(FVCDTOT) LIKE '15.6' THEN 156
		WHEN lower(FVCDTOT) LIKE '4.5' THEN 45
		WHEN lower(FVCDTOT) LIKE '15.5' THEN 155
		WHEN lower(FVCDTOT) LIKE '4.6' THEN 46
		WHEN lower(FVCDTOT) LIKE '15.4' THEN 154
		WHEN lower(FVCDTOT) LIKE '4.7' THEN 47
		WHEN lower(FVCDTOT) LIKE '15.3' THEN 153
		WHEN lower(FVCDTOT) LIKE '4.8' THEN 48
		WHEN lower(FVCDTOT) LIKE '15.2' THEN 152
		WHEN lower(FVCDTOT) LIKE '4.9' THEN 49
		WHEN lower(FVCDTOT) LIKE '26.9' THEN 269
		WHEN lower(FVCDTOT) LIKE '26.6' THEN 266
		WHEN lower(FVCDTOT) LIKE '26.4' THEN 264
		WHEN lower(FVCDTOT) LIKE '26.3' THEN 263
		WHEN lower(FVCDTOT) LIKE '26.2' THEN 262
		WHEN lower(FVCDTOT) LIKE '14.2' THEN 142
		WHEN lower(FVCDTOT) LIKE '14.1' THEN 141
		WHEN lower(FVCDTOT) LIKE '3.1' THEN 31
		WHEN lower(FVCDTOT) LIKE '3.2' THEN 32
		WHEN lower(FVCDTOT) LIKE '3.3' THEN 33
		WHEN lower(FVCDTOT) LIKE '14.9' THEN 149
		WHEN lower(FVCDTOT) LIKE '3.4' THEN 34
		WHEN lower(FVCDTOT) LIKE '14.8' THEN 148
		WHEN lower(FVCDTOT) LIKE '3.5' THEN 35
		WHEN lower(FVCDTOT) LIKE '14.7' THEN 147
		WHEN lower(FVCDTOT) LIKE '3.6' THEN 36
		WHEN lower(FVCDTOT) LIKE '14.6' THEN 146
		WHEN lower(FVCDTOT) LIKE '3.7' THEN 37
		WHEN lower(FVCDTOT) LIKE '14.5' THEN 145
		WHEN lower(FVCDTOT) LIKE '3.8' THEN 38
		WHEN lower(FVCDTOT) LIKE '14.4' THEN 144
		WHEN lower(FVCDTOT) LIKE '3.9' THEN 39
		WHEN lower(FVCDTOT) LIKE '14.3' THEN 143
		WHEN lower(FVCDTOT) LIKE '25.1' THEN 251
		WHEN lower(FVCDTOT) LIKE '25.9' THEN 259
		WHEN lower(FVCDTOT) LIKE '25.7' THEN 257
		WHEN lower(FVCDTOT) LIKE '13.3' THEN 133
		WHEN lower(FVCDTOT) LIKE '13.2' THEN 132
		WHEN lower(FVCDTOT) LIKE '13.1' THEN 131
		WHEN lower(FVCDTOT) LIKE '2.1' THEN 21
		WHEN lower(FVCDTOT) LIKE '2.2' THEN 22
		WHEN lower(FVCDTOT) LIKE '2.3' THEN 23
		WHEN lower(FVCDTOT) LIKE '2.4' THEN 24
		WHEN lower(FVCDTOT) LIKE '2.5' THEN 25
		WHEN lower(FVCDTOT) LIKE '13.9' THEN 139
		WHEN lower(FVCDTOT) LIKE '2.6' THEN 26
		WHEN lower(FVCDTOT) LIKE '13.8' THEN 138
		WHEN lower(FVCDTOT) LIKE '2.7' THEN 27
		WHEN lower(FVCDTOT) LIKE '13.7' THEN 137
		WHEN lower(FVCDTOT) LIKE '2.8' THEN 28
		WHEN lower(FVCDTOT) LIKE '13.6' THEN 136
		WHEN lower(FVCDTOT) LIKE '2.9' THEN 29
		WHEN lower(FVCDTOT) LIKE '13.5' THEN 135
		WHEN lower(FVCDTOT) LIKE '13.4' THEN 134
		WHEN lower(FVCDTOT) LIKE '24.3' THEN 243
		WHEN lower(FVCDTOT) LIKE '24.9' THEN 249
		WHEN lower(FVCDTOT) LIKE '24.7' THEN 247
		WHEN lower(FVCDTOT) LIKE '24.6' THEN 246
		WHEN lower(FVCDTOT) LIKE '24.4' THEN 244
		WHEN lower(FVCDTOT) LIKE '12.4' THEN 124
		WHEN lower(FVCDTOT) LIKE '12.3' THEN 123
		WHEN lower(FVCDTOT) LIKE '12.2' THEN 122
		WHEN lower(FVCDTOT) LIKE '12.1' THEN 121
		WHEN lower(FVCDTOT) LIKE '1.1' THEN 11
		WHEN lower(FVCDTOT) LIKE '1.2' THEN 12
		WHEN lower(FVCDTOT) LIKE '1.3' THEN 13
		WHEN lower(FVCDTOT) LIKE '1.4' THEN 14
		WHEN lower(FVCDTOT) LIKE '1.5' THEN 15
		WHEN lower(FVCDTOT) LIKE '1.6' THEN 16
		WHEN lower(FVCDTOT) LIKE '1.7' THEN 17
		WHEN lower(FVCDTOT) LIKE '12.9' THEN 129
		WHEN lower(FVCDTOT) LIKE '1.8' THEN 18
		WHEN lower(FVCDTOT) LIKE '12.8' THEN 128
		WHEN lower(FVCDTOT) LIKE '9.1' THEN 91
		WHEN lower(FVCDTOT) LIKE '1.9' THEN 19
		WHEN lower(FVCDTOT) LIKE '12.7' THEN 127
		WHEN lower(FVCDTOT) LIKE '9.2' THEN 92
		WHEN lower(FVCDTOT) LIKE '12.6' THEN 126
		WHEN lower(FVCDTOT) LIKE '9.3' THEN 93
		WHEN lower(FVCDTOT) LIKE '12.5' THEN 125
		WHEN lower(FVCDTOT) LIKE '9.4' THEN 94
		WHEN lower(FVCDTOT) LIKE '9.5' THEN 95
		WHEN lower(FVCDTOT) LIKE '9.6' THEN 96
		WHEN lower(FVCDTOT) LIKE '9.7' THEN 97
		WHEN lower(FVCDTOT) LIKE '9.8' THEN 98
		WHEN lower(FVCDTOT) LIKE '9.9' THEN 99
		WHEN lower(FVCDTOT) LIKE '23.3' THEN 233
		WHEN lower(FVCDTOT) LIKE '23.2' THEN 232
		WHEN lower(FVCDTOT) LIKE '23.1' THEN 231
		WHEN lower(FVCDTOT) LIKE '23.7' THEN 237
		WHEN lower(FVCDTOT) LIKE '23.6' THEN 236
		WHEN lower(FVCDTOT) LIKE '19.8' THEN 198
		WHEN lower(FVCDTOT) LIKE '19.7' THEN 197
		WHEN lower(FVCDTOT) LIKE '19.6' THEN 196
		WHEN lower(FVCDTOT) LIKE '11.5' THEN 115
		WHEN lower(FVCDTOT) LIKE '11.4' THEN 114
		WHEN lower(FVCDTOT) LIKE '11.3' THEN 113
		WHEN lower(FVCDTOT) LIKE '11.2' THEN 112
		WHEN lower(FVCDTOT) LIKE '0.1' THEN 1
		WHEN lower(FVCDTOT) LIKE '11.1' THEN 111
		WHEN lower(FVCDTOT) LIKE '0.2' THEN 2
		WHEN lower(FVCDTOT) LIKE '0.3' THEN 3
		WHEN lower(FVCDTOT) LIKE '0.4' THEN 4
		WHEN lower(FVCDTOT) LIKE '0.5' THEN 5
		WHEN lower(FVCDTOT) LIKE '19.5' THEN 195
		WHEN lower(FVCDTOT) LIKE '0.6' THEN 6
		WHEN lower(FVCDTOT) LIKE '19.4' THEN 194
		WHEN lower(FVCDTOT) LIKE '0.7' THEN 7
		WHEN lower(FVCDTOT) LIKE '19.3' THEN 193
		WHEN lower(FVCDTOT) LIKE '0.8' THEN 8
		WHEN lower(FVCDTOT) LIKE '19.2' THEN 192
		WHEN lower(FVCDTOT) LIKE '8.1' THEN 81
		WHEN lower(FVCDTOT) LIKE '0.9' THEN 9
		WHEN lower(FVCDTOT) LIKE '19.1' THEN 191
		WHEN lower(FVCDTOT) LIKE '11.9' THEN 119
		WHEN lower(FVCDTOT) LIKE '8.2' THEN 82
		WHEN lower(FVCDTOT) LIKE '11.8' THEN 118
		WHEN lower(FVCDTOT) LIKE '8.3' THEN 83
		WHEN lower(FVCDTOT) LIKE '11.7' THEN 117
		WHEN lower(FVCDTOT) LIKE '8.4' THEN 84
		WHEN lower(FVCDTOT) LIKE '11.6' THEN 116
		WHEN lower(FVCDTOT) LIKE '8.5' THEN 85
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
		WHEN lower(GEN_08) LIKE 'yes' THEN 1
		WHEN lower(GEN_08) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_08) LIKE 'n/s' THEN null 
		WHEN lower(GEN_08) LIKE 'n/a' THEN null 
		WHEN lower(GEN_08) LIKE 'refusal' THEN null 
		WHEN lower(GEN_08) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_08) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_08) LIKE 'not required' THEN null 
	END AS GEN_08,
	CASE
		WHEN lower(PACDFM) LIKE '110' THEN 110
		WHEN lower(PACDFM) LIKE '232' THEN 232
		WHEN lower(PACDFM) LIKE '111' THEN 111
		WHEN lower(PACDFM) LIKE '112' THEN 112
		WHEN lower(PACDFM) LIKE '113' THEN 113
		WHEN lower(PACDFM) LIKE '114' THEN 114
		WHEN lower(PACDFM) LIKE '236' THEN 236
		WHEN lower(PACDFM) LIKE '115' THEN 115
		WHEN lower(PACDFM) LIKE '237' THEN 237
		WHEN lower(PACDFM) LIKE '116' THEN 116
		WHEN lower(PACDFM) LIKE '238' THEN 238
		WHEN lower(PACDFM) LIKE '117' THEN 117
		WHEN lower(PACDFM) LIKE '239' THEN 239
		WHEN lower(PACDFM) LIKE '118' THEN 118
		WHEN lower(PACDFM) LIKE '119' THEN 119
		WHEN lower(PACDFM) LIKE '10' THEN 10
		WHEN lower(PACDFM) LIKE '11' THEN 11
		WHEN lower(PACDFM) LIKE '12' THEN 12
		WHEN lower(PACDFM) LIKE '13' THEN 13
		WHEN lower(PACDFM) LIKE '14' THEN 14
		WHEN lower(PACDFM) LIKE '15' THEN 15
		WHEN lower(PACDFM) LIKE '16' THEN 16
		WHEN lower(PACDFM) LIKE '17' THEN 17
		WHEN lower(PACDFM) LIKE '18' THEN 18
		WHEN lower(PACDFM) LIKE '19' THEN 19
		WHEN lower(PACDFM) LIKE '240' THEN 240
		WHEN lower(PACDFM) LIKE '120' THEN 120
		WHEN lower(PACDFM) LIKE '242' THEN 242
		WHEN lower(PACDFM) LIKE '121' THEN 121
		WHEN lower(PACDFM) LIKE '0' THEN 0
		WHEN lower(PACDFM) LIKE '122' THEN 122
		WHEN lower(PACDFM) LIKE '1' THEN 1
		WHEN lower(PACDFM) LIKE '123' THEN 123
		WHEN lower(PACDFM) LIKE '2' THEN 2
		WHEN lower(PACDFM) LIKE '245' THEN 245
		WHEN lower(PACDFM) LIKE '124' THEN 124
		WHEN lower(PACDFM) LIKE '3' THEN 3
		WHEN lower(PACDFM) LIKE '125' THEN 125
		WHEN lower(PACDFM) LIKE '4' THEN 4
		WHEN lower(PACDFM) LIKE '126' THEN 126
		WHEN lower(PACDFM) LIKE '5' THEN 5
		WHEN lower(PACDFM) LIKE '127' THEN 127
		WHEN lower(PACDFM) LIKE '6' THEN 6
		WHEN lower(PACDFM) LIKE '128' THEN 128
		WHEN lower(PACDFM) LIKE '7' THEN 7
		WHEN lower(PACDFM) LIKE '129' THEN 129
		WHEN lower(PACDFM) LIKE '8' THEN 8
		WHEN lower(PACDFM) LIKE '9' THEN 9
		WHEN lower(PACDFM) LIKE '20' THEN 20
		WHEN lower(PACDFM) LIKE '21' THEN 21
		WHEN lower(PACDFM) LIKE '22' THEN 22
		WHEN lower(PACDFM) LIKE '23' THEN 23
		WHEN lower(PACDFM) LIKE '24' THEN 24
		WHEN lower(PACDFM) LIKE '25' THEN 25
		WHEN lower(PACDFM) LIKE '26' THEN 26
		WHEN lower(PACDFM) LIKE '27' THEN 27
		WHEN lower(PACDFM) LIKE '28' THEN 28
		WHEN lower(PACDFM) LIKE '29' THEN 29
		WHEN lower(PACDFM) LIKE '251' THEN 251
		WHEN lower(PACDFM) LIKE '130' THEN 130
		WHEN lower(PACDFM) LIKE '131' THEN 131
		WHEN lower(PACDFM) LIKE '132' THEN 132
		WHEN lower(PACDFM) LIKE '133' THEN 133
		WHEN lower(PACDFM) LIKE '134' THEN 134
		WHEN lower(PACDFM) LIKE '256' THEN 256
		WHEN lower(PACDFM) LIKE '135' THEN 135
		WHEN lower(PACDFM) LIKE '257' THEN 257
		WHEN lower(PACDFM) LIKE '136' THEN 136
		WHEN lower(PACDFM) LIKE '137' THEN 137
		WHEN lower(PACDFM) LIKE '138' THEN 138
		WHEN lower(PACDFM) LIKE '139' THEN 139
		WHEN lower(PACDFM) LIKE '30' THEN 30
		WHEN lower(PACDFM) LIKE '31' THEN 31
		WHEN lower(PACDFM) LIKE '32' THEN 32
		WHEN lower(PACDFM) LIKE '33' THEN 33
		WHEN lower(PACDFM) LIKE '34' THEN 34
		WHEN lower(PACDFM) LIKE '35' THEN 35
		WHEN lower(PACDFM) LIKE '36' THEN 36
		WHEN lower(PACDFM) LIKE '37' THEN 37
		WHEN lower(PACDFM) LIKE '38' THEN 38
		WHEN lower(PACDFM) LIKE '39' THEN 39
		WHEN lower(PACDFM) LIKE '140' THEN 140
		WHEN lower(PACDFM) LIKE '141' THEN 141
		WHEN lower(PACDFM) LIKE '263' THEN 263
		WHEN lower(PACDFM) LIKE '142' THEN 142
		WHEN lower(PACDFM) LIKE '143' THEN 143
		WHEN lower(PACDFM) LIKE '144' THEN 144
		WHEN lower(PACDFM) LIKE '145' THEN 145
		WHEN lower(PACDFM) LIKE '267' THEN 267
		WHEN lower(PACDFM) LIKE '146' THEN 146
		WHEN lower(PACDFM) LIKE '147' THEN 147
		WHEN lower(PACDFM) LIKE '148' THEN 148
		WHEN lower(PACDFM) LIKE '149' THEN 149
		WHEN lower(PACDFM) LIKE '40' THEN 40
		WHEN lower(PACDFM) LIKE '41' THEN 41
		WHEN lower(PACDFM) LIKE '42' THEN 42
		WHEN lower(PACDFM) LIKE '43' THEN 43
		WHEN lower(PACDFM) LIKE '44' THEN 44
		WHEN lower(PACDFM) LIKE '45' THEN 45
		WHEN lower(PACDFM) LIKE '46' THEN 46
		WHEN lower(PACDFM) LIKE '47' THEN 47
		WHEN lower(PACDFM) LIKE '48' THEN 48
		WHEN lower(PACDFM) LIKE '49' THEN 49
		WHEN lower(PACDFM) LIKE '271' THEN 271
		WHEN lower(PACDFM) LIKE '150' THEN 150
		WHEN lower(PACDFM) LIKE '151' THEN 151
		WHEN lower(PACDFM) LIKE '273' THEN 273
		WHEN lower(PACDFM) LIKE '152' THEN 152
		WHEN lower(PACDFM) LIKE '153' THEN 153
		WHEN lower(PACDFM) LIKE '154' THEN 154
		WHEN lower(PACDFM) LIKE '155' THEN 155
		WHEN lower(PACDFM) LIKE '156' THEN 156
		WHEN lower(PACDFM) LIKE '278' THEN 278
		WHEN lower(PACDFM) LIKE '157' THEN 157
		WHEN lower(PACDFM) LIKE '158' THEN 158
		WHEN lower(PACDFM) LIKE '159' THEN 159
		WHEN lower(PACDFM) LIKE '50' THEN 50
		WHEN lower(PACDFM) LIKE '51' THEN 51
		WHEN lower(PACDFM) LIKE '52' THEN 52
		WHEN lower(PACDFM) LIKE '53' THEN 53
		WHEN lower(PACDFM) LIKE '54' THEN 54
		WHEN lower(PACDFM) LIKE '55' THEN 55
		WHEN lower(PACDFM) LIKE '56' THEN 56
		WHEN lower(PACDFM) LIKE '57' THEN 57
		WHEN lower(PACDFM) LIKE '58' THEN 58
		WHEN lower(PACDFM) LIKE '59' THEN 59
		WHEN lower(PACDFM) LIKE '160' THEN 160
		WHEN lower(PACDFM) LIKE '161' THEN 161
		WHEN lower(PACDFM) LIKE '162' THEN 162
		WHEN lower(PACDFM) LIKE '163' THEN 163
		WHEN lower(PACDFM) LIKE '164' THEN 164
		WHEN lower(PACDFM) LIKE '165' THEN 165
		WHEN lower(PACDFM) LIKE '166' THEN 166
		WHEN lower(PACDFM) LIKE '167' THEN 167
		WHEN lower(PACDFM) LIKE '168' THEN 168
		WHEN lower(PACDFM) LIKE '169' THEN 169
		WHEN lower(PACDFM) LIKE '60' THEN 60
		WHEN lower(PACDFM) LIKE '61' THEN 61
		WHEN lower(PACDFM) LIKE '62' THEN 62
		WHEN lower(PACDFM) LIKE '63' THEN 63
		WHEN lower(PACDFM) LIKE '64' THEN 64
		WHEN lower(PACDFM) LIKE '65' THEN 65
		WHEN lower(PACDFM) LIKE '66' THEN 66
		WHEN lower(PACDFM) LIKE '67' THEN 67
		WHEN lower(PACDFM) LIKE '68' THEN 68
		WHEN lower(PACDFM) LIKE '69' THEN 69
		WHEN lower(PACDFM) LIKE '170' THEN 170
		WHEN lower(PACDFM) LIKE '171' THEN 171
		WHEN lower(PACDFM) LIKE '172' THEN 172
		WHEN lower(PACDFM) LIKE '173' THEN 173
		WHEN lower(PACDFM) LIKE '174' THEN 174
		WHEN lower(PACDFM) LIKE '176' THEN 176
		WHEN lower(PACDFM) LIKE '177' THEN 177
		WHEN lower(PACDFM) LIKE '178' THEN 178
		WHEN lower(PACDFM) LIKE '179' THEN 179
		WHEN lower(PACDFM) LIKE '70' THEN 70
		WHEN lower(PACDFM) LIKE '71' THEN 71
		WHEN lower(PACDFM) LIKE '72' THEN 72
		WHEN lower(PACDFM) LIKE '73' THEN 73
		WHEN lower(PACDFM) LIKE '74' THEN 74
		WHEN lower(PACDFM) LIKE '75' THEN 75
		WHEN lower(PACDFM) LIKE '76' THEN 76
		WHEN lower(PACDFM) LIKE '77' THEN 77
		WHEN lower(PACDFM) LIKE '78' THEN 78
		WHEN lower(PACDFM) LIKE '79' THEN 79
		WHEN lower(PACDFM) LIKE '180' THEN 180
		WHEN lower(PACDFM) LIKE '181' THEN 181
		WHEN lower(PACDFM) LIKE '182' THEN 182
		WHEN lower(PACDFM) LIKE '183' THEN 183
		WHEN lower(PACDFM) LIKE '184' THEN 184
		WHEN lower(PACDFM) LIKE '185' THEN 185
		WHEN lower(PACDFM) LIKE '186' THEN 186
		WHEN lower(PACDFM) LIKE '188' THEN 188
		WHEN lower(PACDFM) LIKE '189' THEN 189
		WHEN lower(PACDFM) LIKE '80' THEN 80
		WHEN lower(PACDFM) LIKE '81' THEN 81
		WHEN lower(PACDFM) LIKE '82' THEN 82
		WHEN lower(PACDFM) LIKE '83' THEN 83
		WHEN lower(PACDFM) LIKE '84' THEN 84
		WHEN lower(PACDFM) LIKE '85' THEN 85
		WHEN lower(PACDFM) LIKE '86' THEN 86
		WHEN lower(PACDFM) LIKE '87' THEN 87
		WHEN lower(PACDFM) LIKE '88' THEN 88
		WHEN lower(PACDFM) LIKE '89' THEN 89
		WHEN lower(PACDFM) LIKE '190' THEN 190
		WHEN lower(PACDFM) LIKE '192' THEN 192
		WHEN lower(PACDFM) LIKE '193' THEN 193
		WHEN lower(PACDFM) LIKE '195' THEN 195
		WHEN lower(PACDFM) LIKE '198' THEN 198
		WHEN lower(PACDFM) LIKE '199' THEN 199
		WHEN lower(PACDFM) LIKE '90' THEN 90
		WHEN lower(PACDFM) LIKE '91' THEN 91
		WHEN lower(PACDFM) LIKE '92' THEN 92
		WHEN lower(PACDFM) LIKE '93' THEN 93
		WHEN lower(PACDFM) LIKE '94' THEN 94
		WHEN lower(PACDFM) LIKE '95' THEN 95
		WHEN lower(PACDFM) LIKE '96' THEN 96
		WHEN lower(PACDFM) LIKE '97' THEN 97
		WHEN lower(PACDFM) LIKE '98' THEN 98
		WHEN lower(PACDFM) LIKE '99' THEN 99
		WHEN lower(PACDFM) LIKE '201' THEN 201
		WHEN lower(PACDFM) LIKE '202' THEN 202
		WHEN lower(PACDFM) LIKE '203' THEN 203
		WHEN lower(PACDFM) LIKE '325' THEN 325
		WHEN lower(PACDFM) LIKE '205' THEN 205
		WHEN lower(PACDFM) LIKE '206' THEN 206
		WHEN lower(PACDFM) LIKE '207' THEN 207
		WHEN lower(PACDFM) LIKE '209' THEN 209
		WHEN lower(PACDFM) LIKE '210' THEN 210
		WHEN lower(PACDFM) LIKE '216' THEN 216
		WHEN lower(PACDFM) LIKE '217' THEN 217
		WHEN lower(PACDFM) LIKE '218' THEN 218
		WHEN lower(PACDFM) LIKE '100' THEN 100
		WHEN lower(PACDFM) LIKE '222' THEN 222
		WHEN lower(PACDFM) LIKE '101' THEN 101
		WHEN lower(PACDFM) LIKE '102' THEN 102
		WHEN lower(PACDFM) LIKE '103' THEN 103
		WHEN lower(PACDFM) LIKE '225' THEN 225
		WHEN lower(PACDFM) LIKE '104' THEN 104
		WHEN lower(PACDFM) LIKE '105' THEN 105
		WHEN lower(PACDFM) LIKE '106' THEN 106
		WHEN lower(PACDFM) LIKE '228' THEN 228
		WHEN lower(PACDFM) LIKE '107' THEN 107
		WHEN lower(PACDFM) LIKE '229' THEN 229
		WHEN lower(PACDFM) LIKE '108' THEN 108
		WHEN lower(PACDFM) LIKE '109' THEN 109
		WHEN lower(PACDFM) LIKE 'don''t know' THEN null 
		WHEN lower(PACDFM) LIKE 'n/s' THEN null 
		WHEN lower(PACDFM) LIKE 'n/a' THEN null 
		WHEN lower(PACDFM) LIKE 'refusal' THEN null 
		WHEN lower(PACDFM) LIKE 'no drinks last w' THEN null 
		WHEN lower(PACDFM) LIKE 'no phy. activity' THEN null 
		WHEN lower(PACDFM) LIKE 'not required' THEN null 
	END AS PACDFM,
	CASE
		WHEN lower(SPSDCON) LIKE '22' THEN 22
		WHEN lower(SPSDCON) LIKE '23' THEN 23
		WHEN lower(SPSDCON) LIKE '24' THEN 24
		WHEN lower(SPSDCON) LIKE '25' THEN 25
		WHEN lower(SPSDCON) LIKE '26' THEN 26
		WHEN lower(SPSDCON) LIKE '27' THEN 27
		WHEN lower(SPSDCON) LIKE '28' THEN 28
		WHEN lower(SPSDCON) LIKE '29' THEN 29
		WHEN lower(SPSDCON) LIKE '30' THEN 30
		WHEN lower(SPSDCON) LIKE '31' THEN 31
		WHEN lower(SPSDCON) LIKE '10' THEN 10
		WHEN lower(SPSDCON) LIKE '32' THEN 32
		WHEN lower(SPSDCON) LIKE '11' THEN 11
		WHEN lower(SPSDCON) LIKE '33' THEN 33
		WHEN lower(SPSDCON) LIKE '12' THEN 12
		WHEN lower(SPSDCON) LIKE '34' THEN 34
		WHEN lower(SPSDCON) LIKE '13' THEN 13
		WHEN lower(SPSDCON) LIKE '35' THEN 35
		WHEN lower(SPSDCON) LIKE '14' THEN 14
		WHEN lower(SPSDCON) LIKE '36' THEN 36
		WHEN lower(SPSDCON) LIKE '15' THEN 15
		WHEN lower(SPSDCON) LIKE '37' THEN 37
		WHEN lower(SPSDCON) LIKE '16' THEN 16
		WHEN lower(SPSDCON) LIKE '38' THEN 38
		WHEN lower(SPSDCON) LIKE '17' THEN 17
		WHEN lower(SPSDCON) LIKE '39' THEN 39
		WHEN lower(SPSDCON) LIKE '18' THEN 18
		WHEN lower(SPSDCON) LIKE '19' THEN 19
		WHEN lower(SPSDCON) LIKE '40' THEN 40
		WHEN lower(SPSDCON) LIKE '20' THEN 20
		WHEN lower(SPSDCON) LIKE '21' THEN 21
		WHEN lower(SPSDCON) LIKE 'don''t know' THEN null 
		WHEN lower(SPSDCON) LIKE 'n/s' THEN null 
		WHEN lower(SPSDCON) LIKE 'n/a' THEN null 
		WHEN lower(SPSDCON) LIKE 'refusal' THEN null 
		WHEN lower(SPSDCON) LIKE 'no drinks last w' THEN null 
		WHEN lower(SPSDCON) LIKE 'no phy. activity' THEN null 
		WHEN lower(SPSDCON) LIKE 'not required' THEN null 
	END AS SPSDCON,
	CASE
		WHEN lower(PCU_153) LIKE '<once every 3 yr' THEN 4
		WHEN lower(PCU_153) LIKE 'once every 3 yrs' THEN 5
		WHEN lower(PCU_153) LIKE 'once every 2 yrs' THEN 3
		WHEN lower(PCU_153) LIKE 'once a year' THEN 1
		WHEN lower(PCU_153) LIKE '> once a year' THEN 2
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
		WHEN lower(GENGSWL) LIKE 'neither nor' THEN 2
		WHEN lower(GENGSWL) LIKE 'satisfied' THEN 3
		WHEN lower(GENGSWL) LIKE 'dissatisfied' THEN 1
		WHEN lower(GENGSWL) LIKE 'very satisfied' THEN 4
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
		WHEN lower(ADLF6R) LIKE 'yes' THEN 1
		WHEN lower(ADLF6R) LIKE 'don''t know' THEN null 
		WHEN lower(ADLF6R) LIKE 'n/s' THEN null 
		WHEN lower(ADLF6R) LIKE 'n/a' THEN null 
		WHEN lower(ADLF6R) LIKE 'refusal' THEN null 
		WHEN lower(ADLF6R) LIKE 'no drinks last w' THEN null 
		WHEN lower(ADLF6R) LIKE 'no phy. activity' THEN null 
		WHEN lower(ADLF6R) LIKE 'not required' THEN null 
	END AS ADLF6R,
	CASE
		WHEN lower(INCGHH) LIKE '$20,000-$39,999' THEN 2
		WHEN lower(INCGHH) LIKE 'no or <$20,000' THEN 1
		WHEN lower(INCGHH) LIKE '$40,000-$59,999' THEN 3
		WHEN lower(INCGHH) LIKE '$60,000-$79,999' THEN 4
		WHEN lower(INCGHH) LIKE '$80,000 or more' THEN 5
		WHEN lower(INCGHH) LIKE 'don''t know' THEN null 
		WHEN lower(INCGHH) LIKE 'n/s' THEN null 
		WHEN lower(INCGHH) LIKE 'n/a' THEN null 
		WHEN lower(INCGHH) LIKE 'refusal' THEN null 
		WHEN lower(INCGHH) LIKE 'no drinks last w' THEN null 
		WHEN lower(INCGHH) LIKE 'no phy. activity' THEN null 
		WHEN lower(INCGHH) LIKE 'not required' THEN null 
	END AS INCGHH,
	CASE
		WHEN lower(GENDHDI) LIKE 'very good' THEN 4
		WHEN lower(GENDHDI) LIKE 'excellent' THEN 5
		WHEN lower(GENDHDI) LIKE 'poor' THEN 1
		WHEN lower(GENDHDI) LIKE 'fair' THEN 2
		WHEN lower(GENDHDI) LIKE 'good' THEN 3
		WHEN lower(GENDHDI) LIKE 'don''t know' THEN null 
		WHEN lower(GENDHDI) LIKE 'n/s' THEN null 
		WHEN lower(GENDHDI) LIKE 'n/a' THEN null 
		WHEN lower(GENDHDI) LIKE 'refusal' THEN null 
		WHEN lower(GENDHDI) LIKE 'no drinks last w' THEN null 
		WHEN lower(GENDHDI) LIKE 'no phy. activity' THEN null 
		WHEN lower(GENDHDI) LIKE 'not required' THEN null 
	END AS GENDHDI,
	CASE
		WHEN lower(PMH_04) LIKE 'once or twice' THEN 1
		WHEN lower(PMH_04) LIKE 'never' THEN 0
		WHEN lower(PMH_04) LIKE '2 or 3 times\wk' THEN 3
		WHEN lower(PMH_04) LIKE 'every day' THEN 5
		WHEN lower(PMH_04) LIKE 'almost every day' THEN 4
		WHEN lower(PMH_04) LIKE 'once a week' THEN 2
		WHEN lower(PMH_04) LIKE 'don''t know' THEN null 
		WHEN lower(PMH_04) LIKE 'n/s' THEN null 
		WHEN lower(PMH_04) LIKE 'n/a' THEN null 
		WHEN lower(PMH_04) LIKE 'refusal' THEN null 
		WHEN lower(PMH_04) LIKE 'no drinks last w' THEN null 
		WHEN lower(PMH_04) LIKE 'no phy. activity' THEN null 
		WHEN lower(PMH_04) LIKE 'not required' THEN null 
	END AS PMH_04,
	CASE
		WHEN lower(GEN_02B) LIKE 'very good' THEN 4
		WHEN lower(GEN_02B) LIKE 'excellent' THEN 5
		WHEN lower(GEN_02B) LIKE 'poor' THEN 1
		WHEN lower(GEN_02B) LIKE 'fair' THEN 2
		WHEN lower(GEN_02B) LIKE 'good' THEN 3
		WHEN lower(GEN_02B) LIKE 'don''t know' THEN null 
		WHEN lower(GEN_02B) LIKE 'n/s' THEN null 
		WHEN lower(GEN_02B) LIKE 'n/a' THEN null 
		WHEN lower(GEN_02B) LIKE 'refusal' THEN null 
		WHEN lower(GEN_02B) LIKE 'no drinks last w' THEN null 
		WHEN lower(GEN_02B) LIKE 'no phy. activity' THEN null 
		WHEN lower(GEN_02B) LIKE 'not required' THEN null 
	END AS GEN_02B,
	CASE
		WHEN lower(GENDMHI) LIKE 'very good' THEN 4
		WHEN lower(GENDMHI) LIKE 'excellent' THEN 5
		WHEN lower(GENDMHI) LIKE 'poor' THEN 1
		WHEN lower(GENDMHI) LIKE 'fair' THEN 2
		WHEN lower(GENDMHI) LIKE 'good' THEN 3
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