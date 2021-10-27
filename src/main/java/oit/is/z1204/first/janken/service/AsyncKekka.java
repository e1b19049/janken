package oit.is.z1204.first.janken.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1204.first.janken.model.Match;
import oit.is.z1204.first.janken.model.MatchInfo;
import oit.is.z1204.first.janken.model.MatchMapper;
import oit.is.z1204.first.janken.model.MatchInfoMapper;

@Service
public class AsyncKekka {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;

  @Async
  public void kekka(SseEmitter emitter) {
    dbUpdated = true;
    logger.info("kekka start");
    try {
      while (true) {
        TimeUnit.MICROSECONDS.sleep(500);
        ArrayList<Match> matches = matchMapper.selectAllMatches();
        for (Match match : matches) {
          if (!match.isActive()) {
            continue;
          }
          emitter.send(match);
          ArrayList<MatchInfo> matchInfos = matchInfoMapper.selectAllMatchInfo();
          for (MatchInfo matchInfo : matchInfos) {
            if (matchInfo.getId() == match.getMatchinfo()) {
              matchInfoMapper.updateById(matchInfo);
            }
          }
          dbUpdated = false;
          matchMapper.updateById(match);
        }
      }
    } catch (Exception e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
      emitter.complete();
    }
  }

}
