package com.lunchtasting.server.biz.temporaryEnroll;

import com.lunchtasting.server.po.temporaryEnroll.TemporaryCinema;

public interface TemporaryCinemaBIZ {
	Long addCinema(TemporaryCinema temporaryCinema);
	int checkRandom(String random);
	TemporaryCinema getCinema(String id);
}
