package ch.heigvd.amt.gamification.services;

import ch.heigvd.amt.gamification.dto.RuleDTO;
import javax.ejb.Local;

/**
 *
 * @author Raphaël Racine
 */
@Local
public interface RulesProcessorLocal {
    public void processPostRule(RuleDTO ruleDTO);
}
