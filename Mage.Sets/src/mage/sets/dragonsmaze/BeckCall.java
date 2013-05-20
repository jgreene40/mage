/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */

package mage.sets.dragonsmaze;

import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DrawCardControllerEffect;
import mage.cards.SplitCard;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

/**
 *
 * @author LevelX2
 */


public class BeckCall extends SplitCard<BeckCall> {

    public BeckCall(UUID ownerId) {
        super(ownerId, 123, "Beck", "Call", Rarity.RARE, new CardType[]{CardType.SORCERY}, "{G}{U}", "{4}{W}{U}", true);
        this.expansionSetCode = "DGM";

        this.color.setWhite(true);
        this.color.setBlue(true);
        this.color.setGreen(true);

        // Beck
        // Whenever a creature enters the battlefield this turn, you may draw a card.
        getLeftHalfCard().getColor().setGreen(true);
        getLeftHalfCard().getColor().setBlue(true);
        getLeftHalfCard().getSpellAbility().addEffect(new CreateDelayedTriggeredAbilityEffect(new BeckTriggeredAbility()));

        // Call
        // Put four 1/1 white Bird creature tokens with flying onto the battlefield.
        getRightHalfCard().getColor().setWhite(true);
        getRightHalfCard().getColor().setBlue(true);
        getRightHalfCard().getSpellAbility().addEffect(new CreateTokenEffect(new BirdToken(),4));

    }

    public BeckCall(final BeckCall card) {
        super(card);
    }

    @Override
    public BeckCall copy() {
        return new BeckCall(this);
    }
}

class BeckTriggeredAbility extends DelayedTriggeredAbility<BeckTriggeredAbility> {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    public BeckTriggeredAbility() {
        super(new DrawCardControllerEffect(1), Constants.Duration.EndOfTurn, false);
        optional = true;
    }

    public BeckTriggeredAbility(BeckTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.ENTERS_THE_BATTLEFIELD) {
            UUID targetId = event.getTargetId();
            Permanent permanent = game.getPermanent(targetId);
            if (filter.match(permanent, getSourceId(), getControllerId(), game)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BeckTriggeredAbility copy() {
        return new BeckTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever a creature enters the battlefield this turn, you may " + modes.getText();
    }
}