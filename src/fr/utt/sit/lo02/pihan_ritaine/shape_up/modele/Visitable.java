package fr.utt.sit.lo02.pihan_ritaine.shape_up.modele;
/**
 * Interface visitée par l'interface visitor.
 * @author Yaëlle Pihan et Thomas Ritaine
 * @version 1.0
 *
 */

public interface Visitable {
    int accept(Visitor v);

}
