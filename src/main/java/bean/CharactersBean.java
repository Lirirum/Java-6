/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import entity.Characters;
import entity.service.CharactersFacadeREST;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;

import java.util.List;

@Named
@RequestScoped
public class CharactersBean {

    @Inject
    private CharactersFacadeREST charactersFacade;
    private Characters selectedCharacter; 
    private List<Characters> charactersList;
    private String fullName;

    
      public CharactersBean() {
        this.charactersFacade = new CharactersFacadeREST();
        this.selectedCharacter = new Characters();
        loadCharacters();
    }
   
    public void loadCharacters() {
        charactersList = charactersFacade.findAll();
    }
        
    public List<Characters> getCharactersList() {
        return charactersList;
    }

    public void setCharactersList(List<Characters> charactersList) {
        this.charactersList = charactersList;
    }
    public Characters getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(Characters selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
    
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName =fullName;
    }
    
    
    public void createCharacter() {
        this.charactersFacade.createUser(selectedCharacter);
        loadCharacters();
        
    }
    
    public void getCharacter(){
        if (selectedCharacter.getId() != null) {
            selectedCharacter =    this.charactersFacade.findById(selectedCharacter.getId());
        }
    }
    
    public void getCharacterByFullName(){
        if (fullName != null) {
            selectedCharacter =    this.charactersFacade.findByFullName(fullName);
        }
    }
    
    public String updateCharacter() {
        if (selectedCharacter.getId() != null) {
            charactersFacade.updateCharacter(selectedCharacter);
        }
        loadCharacters();
        selectedCharacter = new Characters();
        return "characters";
    }
    
    public String editCharacter(Characters character) {
        
        this.selectedCharacter = character;
        return "characterEdit?faces-redirect=true"; 
    }
    
    public void deleteCharacter(Characters character) {
         if (character.getId() != null) {
            charactersFacade.deleteCharacter(character.getId());
        }    
        loadCharacters();
    }
}
