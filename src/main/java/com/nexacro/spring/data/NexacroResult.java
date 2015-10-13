package com.nexacro.spring.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nexacro.spring.resolve.NexacroHandlerMethodReturnValueHandler;
import com.nexacro.xapi.data.DataSet;
import com.nexacro.xapi.data.PlatformData;
import com.nexacro.xapi.data.Variable;

/**
 * <pre>
 * nexacro platform으로 데이터를 송신하기 위한 정보를 가진다.
 * 데이터 삽입 시 변환은 이루어지지 않으며, NexacroHandlerMethodReturnValueHandler에서 데이터 변환이 이루어진다.
 * </pre>
 *
 * @ClassName   : NexacroResult.java
 * @Description : 클래스 설명을 기술합니다.
 * @author Park SeongMin
 * @since 2015. 7. 27.
 * @version 1.0
 * @see NexacroHandlerMethodReturnValueHandler
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015. 7. 27.     Park SeongMin     최초 생성
 * </pre>
 */
public class NexacroResult {

    private PlatformData platformData;

    private Map<String, List> dataSetMaps;
    private Map<String, Object> variableMaps;
    
    // result status
    private int errorCode;
    private String errorMsg;
    
    private boolean registedError = false;
    
    public NexacroResult() {
        initResult();
    }
    
    private void initResult() {
        dataSetMaps = new HashMap<String, List>();
        variableMaps = new HashMap<String, Object>();
        platformData = new PlatformData();
    }
    
    /**
     * 
     * <code>DataSet<code>을 추가한다.
     *
     * @param dataSet
     */
    public void addDataSet(DataSet dataSet) {
        platformData.addDataSet(dataSet);
    }
    
    /**
     * 
     * 입력받은 dataSetName(DataSet 이름)으로  List를 <code>DataSet</code>으로 추가한다.
     * <p><code>List</code>의 값은 <code>java.util.Map<code> 혹은 VO class만 설정가능하다.
     *
     * @param dataSetName
     * @param beans
     */
    public void addDataSet(String dataSetName, List beans) {
        checkName(dataSetName);
        checkBean(beans);
        
        dataSetMaps.put(dataSetName, beans);
    }
    
    /**
     * 
     * <code>Variable</code>을 추가한다.
     *
     * @param variable
     */
    public void addVariable(Variable variable) {
        platformData.addVariable(variable);
    }
    
    /**
     * 
     * 입력받은 variableName(Variable 이름)으로  Object를 <code>Variable</code>으로 추가한다.
     *
     * @param variableName
     * @param object
     */
    public void addVariable(String variableName, Object object) {
        checkName(variableName);
        
        variableMaps.put(variableName, object);
    }
    
    public Map<String, List> getDataSets() {
        return Collections.unmodifiableMap(dataSetMaps);
    }
    
    public Map<String, Object> getVariables() {
        return Collections.unmodifiableMap(variableMaps);
    }
    
    private void checkName(String dataName) {
        if(dataName == null) {
            throw new IllegalArgumentException("No name specified");
        }
    }
    
    private void checkBean(List bean) {
    }
    
    public PlatformData getPlatformData() {
        return platformData;
    }

    public void setPlatformData(PlatformData platformData) {
        this.platformData = platformData;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.registedError = true;
        this.errorCode = errorCode;
    }
    
    public boolean registedErrorCode() {
        return this.registedError;
    }
    
    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }
    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
}