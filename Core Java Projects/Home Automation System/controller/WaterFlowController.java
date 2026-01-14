package controller;

import deviceModel.WaterFlowControllable;

public class WaterFlowController {

	WaterFlowControllable wfc;
	
	
	
	public WaterFlowController(WaterFlowControllable wfc) {
		this.wfc = wfc;
	}
	public void increaseWaterFlow(int s) {
		wfc.increaseWaterFlow(s);
	}
	public void decreaseWaterFlow(int s) {
		wfc.decreaseWaterFlow(s);
	}
	public void getCurrentWaterFlowLevel() {
		wfc.getCurrentWaterFlowLevel();
	}
}
