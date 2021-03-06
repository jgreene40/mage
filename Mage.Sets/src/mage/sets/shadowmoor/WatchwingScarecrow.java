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
package mage.sets.shadowmoor;

import java.util.UUID;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalContinousEffect;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.mageobject.ColorPredicate;

/**
 *
 * @author jeffwadsworth
 */
public class WatchwingScarecrow extends CardImpl {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("a white creature");
    private static final FilterControlledCreaturePermanent filter2 = new FilterControlledCreaturePermanent("a blue creature");

    static {
        filter.add(new ColorPredicate(ObjectColor.WHITE));
        filter2.add(new ColorPredicate(ObjectColor.BLUE));
    }

    private static final String rule = "{this} has vigilance as long as you control a white creature";
    private static final String rule2 = "{this} has flying as long as you control a blue creature";

    public WatchwingScarecrow(UUID ownerId) {
        super(ownerId, 268, "Watchwing Scarecrow", Rarity.COMMON, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{4}");
        this.expansionSetCode = "SHM";
        this.subtype.add("Scarecrow");
        this.power = new MageInt(2);
        this.toughness = new MageInt(4);

        // Watchwing Scarecrow has vigilance as long as you control a white creature.
        ContinuousEffect effect = new GainAbilitySourceEffect(VigilanceAbility.getInstance(), Duration.WhileOnBattlefield);
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new ConditionalContinousEffect(effect, new PermanentsOnTheBattlefieldCondition(filter), rule)));

        // Watchwing Scarecrow has flying as long as you control a blue creature.
        ContinuousEffect effect2 = new GainAbilitySourceEffect(FlyingAbility.getInstance(), Duration.WhileOnBattlefield);
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new ConditionalContinousEffect(effect2, new PermanentsOnTheBattlefieldCondition(filter2), rule2)));

    }

    public WatchwingScarecrow(final WatchwingScarecrow card) {
        super(card);
    }

    @Override
    public WatchwingScarecrow copy() {
        return new WatchwingScarecrow(this);
    }
}
