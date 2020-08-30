package net.telepathicgrunt.bumblezone.entities.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.telepathicgrunt.bumblezone.entities.controllers.HoneySlimeMoveHelperController;
import net.telepathicgrunt.bumblezone.entities.mobs.HoneySlimeEntity;

import java.util.EnumSet;

public class FacingRevengeGoal extends HurtByTargetGoal {
    private final HoneySlimeEntity slime;

    public FacingRevengeGoal(HoneySlimeEntity slimeIn) {
        super(slimeIn);
        this.slime = slimeIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
    }
    public boolean shouldContinueExecuting() {
        return slime.getAttackTarget() != null && super.shouldContinueExecuting();
    }

    public void tick() {
        this.slime.faceEntity(this.slime.getAttackTarget(), 10.0F, 10.0F);
        ((HoneySlimeMoveHelperController) this.slime.getMoveHelper()).setDirection(this.slime.rotationYaw, this.slime.canDamagePlayer());
    }
}