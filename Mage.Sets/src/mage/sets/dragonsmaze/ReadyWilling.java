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
import mage.Constants.Duration;
import mage.Constants.Rarity;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.IndestructibleAllEffect;
import mage.abilities.effects.common.UntapAllControllerEffect;
import mage.abilities.effects.common.continious.GainAbilityControlledEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.FuseAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.Card;
import mage.cards.SplitCard;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.permanent.token.Token;

/**
 *
 * @author LevelX2
 */
public class ReadyWilling extends SplitCard<ReadyWilling> {

    public ReadyWilling(UUID ownerId) {
        super(ownerId, 132, "Ready - Willing", Rarity.RARE, new CardType[]{CardType.INSTANT}, "{1}{G}{W}{1}{W}{B}");
        this.expansionSetCode = "DGM";

        this.color.setGreen(true);
        this.color.setWhite(true);
        this.color.setBlack(true);

        // Ready
        Card leftHalfCard = this.createLeftHalfCard("Ready", "{1}{G}{W}");
        // Creatures you control are indestructible this turn. Untap each creature you control.
        leftHalfCard.getSpellAbility().addEffect(new IndestructibleAllEffect(new FilterControlledCreaturePermanent("Creatures you controll"), Constants.Duration.EndOfTurn));
        leftHalfCard.getSpellAbility().addEffect(new UntapAllControllerEffect(new FilterControlledCreaturePermanent(),"Untap each creature you control"));

        // Willing
        Card rightHalfCard = this.createRightHalfCard("Willing", "{1}{W}{B}");
        // Creatures you control gain deathtouch and lifelink until end of turn.
        rightHalfCard.getSpellAbility().addEffect(new GainAbilityControlledEffect(DeathtouchAbility.getInstance(), Duration.EndOfTurn, new FilterCreaturePermanent("Creatures")) );
        Effect effect = new GainAbilityControlledEffect(LifelinkAbility.getInstance(), Duration.EndOfTurn, new FilterCreaturePermanent("Creatures"));
        effect.setText("Creatures you control gain lifelink until end of turn.");
        rightHalfCard.getSpellAbility().addEffect(effect);

        // Fuse (You may cast one or both halves of this card from your hand.)
        this.addAbility(new FuseAbility(this, this.getManaCost()));
    }

    public ReadyWilling(final ReadyWilling card) {
        super(card);
    }

    @Override
    public ReadyWilling copy() {
        return new ReadyWilling(this);
    }

    private class WeirdToken extends Token {

        private WeirdToken() {
            super("Weird", "0/1 red Weird");
            cardType.add(Constants.CardType.CREATURE);
            color = ObjectColor.RED;
            subtype.add("Weird");
            power = new MageInt(0);
            toughness = new MageInt(1);
        }

    }

}