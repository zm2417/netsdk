package com.netsdk.lib.structure.customize;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.Utils;
import com.netsdk.lib.structure.NET_TIME;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 47081
 * @version 1.0
 * @description ERR210301049结构体, 日常使用以structure包和NetSDKLib下的同名结构体为主
 * @date 2021/3/17
 */
public class MEDIAFILE_FACERECOGNITION_INFO extends NetSDKLib.SdkStructure {
  /** 结构体大小 */
  public int dwSize;
  /** 报警发生时间 */
  public NET_TIME stTime;
  /** 报警发生地点 */
  public byte[] szAddress = new byte[260];
  /** 通道号 */
  public int nChannelId;
  /** 当前人脸匹配到的候选对象(扩展结构体) 数量 */
  public int nCandidateExNum;
  /** 当前人脸匹配到的候选对象信息, 实际返回个数同nCandidateNum */
  // public CANDIDATE_INFO_CEX[] stuCandidatesEx = (CANDIDATE_INFO_CEX[]) new CANDIDATE_INFO_CEX().toArray(50);
  public CANDIDATE_INFO_CEX[] stuCandidatesEx = new CANDIDATE_INFO_CEX[50];

  public MEDIAFILE_FACERECOGNITION_INFO() {

    for(int i=0;i<stuCandidatesEx.length;i++){
      stuCandidatesEx[i]=new CANDIDATE_INFO_CEX();
    }

    this.dwSize = this.size();
  }

  public void setAddress(String address) {
    byte[] data = address.getBytes(Charset.forName(Utils.getPlatformEncode()));
    System.arraycopy(data, 0, szAddress, 0, data.length);
  }

  public String getAddress() {
    return new String(szAddress, Charset.forName(Utils.getPlatformEncode())).trim();
  }

  public List<CANDIDATE_INFO_CEX> getCandidateInfos() {
    return new ArrayList<>(Arrays.asList(stuCandidatesEx).subList(0, nCandidateExNum));
  }

  @Override
  public String toString() {
    return "MEDIAFILE_FACERECOGNITION_INFO{"
            + "stTime="
            + stTime.toStringTime()
            + ", szAddress="
            + getAddress()
            + ", nChannelId="
            + nChannelId
            + ", nCandidateExNum="
            + nCandidateExNum
            + ", stuCandidatesEx="
            + getCandidateInfos().toString()
            + '}';
  }
}
