package com.acme.learning.platform.learning.domain.model.valueobjects;

import com.acme.learning.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.learning.platform.learning.domain.model.entities.Tutorial;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class LearningPath {
    @OneToMany(mappedBy = "course")
    private List<LearningPathItem> learningPathItems;

    public LearningPath() {
        this.learningPathItems = new ArrayList<>();
    }

    public void addItem(Tutorial tutorial, Long nextItemId) {
        LearningPathItem learningPathItem = new LearningPathItem(tutorial, nextItemId);
        learningPathItems.add(learningPathItem);
    }

    public void addItem(Tutorial tutorial) {
        int size = learningPathItems.size();
        Long currentLastItemId = size > 0 ? learningPathItems.get(size - 1).getId() : null;
        LearningPathItem learningPathItem = new LearningPathItem(tutorial, currentLastItemId);
        learningPathItems.add(learningPathItem);
    }

    public Long getFirstTutorialIdInLearningPath() {
        return learningPathItems.get(0).getTutorial().getId();
    }

    public Tutorial getFirstTutorialInLearningPath() {
        return learningPathItems.get(0).getTutorial();
    }

    public Tutorial getNextTutorialInLearningPath(Long currentTutorialId) {
        Long itemId = learningPathItems.stream()
                .filter(learningPathItem -> learningPathItem.getTutorial().getId().equals(currentTutorialId))
                .findFirst().map(LearningPathItem::getNextItemId).orElse(null);
        return itemId != null ? getLearningPathItemWithTutorialId(itemId).getTutorial() : null;

    }

    private LearningPathItem getLearningPathItemWithId(Long itemId) {
        return learningPathItems.stream().filter(learningPathItem ->
                learningPathItem.getId().equals(itemId)).findFirst().orElse(null);
    }

    private LearningPathItem getLearningPathItemWithTutorialId(Long tutorialId) {
        return learningPathItems.stream().filter(learningPathItem -> learningPathItem.getTutorial().getId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    public boolean isLastTutorialInLearningPath(Long currentTutorialId) {
        return getLearningPathItemWithTutorialId(currentTutorialId).getNextItemId() == null;
    }

    public LearningPathItem getLastItemInLearningPath() {
        return learningPathItems.stream().filter(item -> item.getNextItemId() == null)
                .findFirst().orElse(null);
    }
}
