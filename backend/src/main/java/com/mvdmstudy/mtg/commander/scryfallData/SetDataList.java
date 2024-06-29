package com.mvdmstudy.mtg.commander.scryfallData;

import com.mvdmstudy.mtg.commander.setData.SetData;

import java.util.List;

public record SetDataList(String object, Boolean has_more, List<SetData> data) {
}
