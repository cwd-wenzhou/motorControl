package com.imc.motorcontrol.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@TableName("motor_data")
public class Motor implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT-8")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp timestamp;

    private Short statusCode;
    private Short controlCode;
    private Short modeCode;
    private Short errorCode;

    private Short referenceMechanicalPosition;
    private Short actualMechanicalPosition;
    private Short referenceMechanicalSpeed;
    private Short actualMechanicalSpeed;

    private Short referenceCurrentId;
    private Short actualCurrentId;
    private Short referenceCurrentIq;
    private Short actualCurrentIq;

    private Short uCurrent;
    private Short vCurrent;

    private Short voltageVd;
    private Short voltageVq;
    private Short positionRingKp;
    private Short positionRingKd;
    private Short speedRingKp;
    private Short speedRingKi;
    private Short currentRingKp;
    private Short currentRingKi;

    public Motor(byte @NotNull [] data){
        this.statusCode = ((short) (((data[2] & 0xFF) << 8) | (data[3] & 0xFF)));
        this.controlCode = ((short) (((data[4] & 0xFF) << 8) | (data[5] & 0xFF)));
        this.modeCode=((short) (((data[6] & 0xFF) << 8) | (data[7] & 0xFF)));
        this.errorCode=((short) (((data[8] & 0xFF) << 8) | (data[9] & 0xFF)));

        this.referenceMechanicalPosition =((short) (((data[10] & 0xFF) << 8) | (data[11] & 0xFF)));
        this.actualMechanicalPosition=((short) (((data[12] & 0xFF) << 8) | (data[13] & 0xFF)));
        this.referenceMechanicalSpeed=((short) (((data[14] & 0xFF) << 8) | (data[15] & 0xFF)));
        this.actualMechanicalSpeed=((short) (((data[16] & 0xFF) << 8) | (data[17] & 0xFF)));

        this.referenceCurrentId=((short) (((data[18] & 0xFF) << 8) | (data[19] & 0xFF)));
        this.actualCurrentId=((short) (((data[20] & 0xFF) << 8) | (data[21] & 0xFF)));
        this.referenceCurrentIq=((short) (((data[22] & 0xFF) << 8) | (data[23] & 0xFF)));
        this.actualCurrentIq=((short) (((data[24] & 0xFF) << 8) | (data[25] & 0xFF)));

        this.uCurrent=((short) (((data[26] & 0xFF) << 8) | (data[27] & 0xFF)));
        this.vCurrent=((short) (((data[28] & 0xFF) << 8) | (data[29] & 0xFF)));

        this.voltageVd=((short) (((data[30] & 0xFF) << 8) | (data[31] & 0xFF)));
        this.voltageVq=((short) (((data[32] & 0xFF) << 8) | (data[33] & 0xFF)));
        this.positionRingKp=((short) (((data[34] & 0xFF) << 8) | (data[35] & 0xFF)));
        this.positionRingKd=((short) (((data[36] & 0xFF) << 8) | (data[37] & 0xFF)));
        this.speedRingKp=((short) (((data[38] & 0xFF) << 8) | (data[39] & 0xFF)));
        this.speedRingKi=((short) (((data[40] & 0xFF) << 8) | (data[41] & 0xFF)));
        this.currentRingKp=((short) (((data[42] & 0xFF) << 8) | (data[43] & 0xFF)));
        this.currentRingKi=((short) (((data[44] & 0xFF) << 8) | (data[45] & 0xFF)));
    }

    public Motor() {
        this.statusCode = 0;
        this.controlCode = 0;
        this.modeCode = 0;
        this.errorCode = 0;
        this.referenceMechanicalPosition = 0;
        this.actualMechanicalPosition = 0;
        this.referenceMechanicalSpeed = 0;
        this.actualMechanicalSpeed = 0;
        this.referenceCurrentId = 0;
        this.actualCurrentId = 0;
        this.referenceCurrentIq = 0;
        this.actualCurrentIq = 0;
        this.uCurrent = 0;
        this.vCurrent = 0;
        this.voltageVd = 0;
        this.voltageVq = 0;
        this.positionRingKp = 0;
        this.positionRingKd = 0;
        this.speedRingKp = 0;
        this.speedRingKi = 0;
        this.currentRingKp = 0;
        this.currentRingKi = 0;
    }

    public byte[] getBytes() throws IOException {
        byte[]  res = new byte[48];
        res[0] = 0x33;
        res[1] = 0x33;

        res[2] = (byte) ((statusCode>>8) & 0xff);
        res[3] = (byte) (statusCode & 0xff);
        res[4] = (byte) ((controlCode>>8) & 0xff);
        res[5] = (byte) (controlCode & 0xff);
        res[6] = (byte) ((modeCode>>8) & 0xff);
        res[7] = (byte) (modeCode & 0xff);
        res[8] = (byte) ((errorCode>>8) & 0xff);
        res[9] = (byte) (errorCode & 0xff);

        res[10] = (byte) ((referenceMechanicalPosition>>8) & 0xff);
        res[11] = (byte) (referenceMechanicalPosition & 0xff);
        res[12] = (byte) ((actualMechanicalPosition>>8) & 0xff);
        res[13] = (byte) (actualMechanicalPosition & 0xff);
        res[14] = (byte) ((referenceMechanicalSpeed>>8) & 0xff);
        res[15] = (byte) (referenceMechanicalSpeed & 0xff);
        res[16] = (byte) ((actualMechanicalSpeed>>8) & 0xff);
        res[17] = (byte) (actualMechanicalSpeed & 0xff);

        res[18] = (byte) ((referenceCurrentId>>8) & 0xff);
        res[19] = (byte) (referenceCurrentId & 0xff);
        res[20] = (byte) ((actualCurrentId>>8) & 0xff);
        res[21] = (byte) (actualCurrentId & 0xff);
        res[22] = (byte) ((referenceCurrentIq>>8) & 0xff);
        res[23] = (byte) (referenceCurrentIq & 0xff);
        res[24] = (byte) ((actualCurrentIq>>8) & 0xff);
        res[25] = (byte) (actualCurrentIq & 0xff);

        res[26] = (byte) ((uCurrent>>8) & 0xff);
        res[27] = (byte) (uCurrent & 0xff);
        res[28] = (byte) ((vCurrent>>8) & 0xff);
        res[29] = (byte) (vCurrent & 0xff);

        res[30] = (byte) ((voltageVd>>8) & 0xff);
        res[31] = (byte) (voltageVd & 0xff);
        res[32] = (byte) ((voltageVq>>8) & 0xff);
        res[33] = (byte) (voltageVq & 0xff);
        res[34] = (byte) ((positionRingKp>>8) & 0xff);
        res[35] = (byte) (positionRingKp & 0xff);
        res[36] = (byte) ((positionRingKd>>8) & 0xff);
        res[37] = (byte) (positionRingKd & 0xff);
        res[38] = (byte) ((speedRingKp>>8) & 0xff);
        res[39] = (byte) (speedRingKp & 0xff);
        res[40] = (byte) ((speedRingKi>>8) & 0xff);
        res[41] = (byte) (speedRingKi & 0xff);
        res[42] = (byte) ((currentRingKp>>8) & 0xff);
        res[43] = (byte) (currentRingKp & 0xff);
        res[44] = (byte) ((currentRingKi>>8) & 0xff);
        res[45] = (byte) (currentRingKi & 0xff);

        res[46] = 0x55;
        res[47] = 0x55;

        return res;
    }


}
