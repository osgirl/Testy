package com.sdl.selenium.extjs6.grid;

import com.google.common.base.Strings;
import com.sdl.selenium.extjs6.form.CheckBox;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;

public class Cell extends com.sdl.selenium.web.table.Cell {

    public Cell() {
        super();
    }

    public Cell(WebLocator container) {
        super(container);
    }

    public Cell(WebLocator container, int columnIndex) {
        super(container, columnIndex);
    }

    public Cell(String columnText, SearchType... searchTypes) {
        super(columnText, searchTypes);
    }

    public Cell(int columnIndex, String columnText, SearchType... searchTypes) {
        super(columnIndex, columnText, searchTypes);
    }

    public Cell(WebLocator container, int columnIndex, String columnText, SearchType... searchTypes) {
        super(container, columnIndex);
        setText(columnText, searchTypes);
    }

    public void check() {
        scrollInGrid(this);
        if (!isChecked()) {
            CheckBox checkBox = new CheckBox(this).setBaseCls("x-grid-checkcolumn");
            checkBox.click();
        }
    }

    public void unCheck() {
        scrollInGrid(this);
        if (isChecked()) {
            CheckBox checkBox = new CheckBox(this).setBaseCls("x-grid-checkcolumn");
            checkBox.click();
        }
    }

    public Boolean isChecked() {
        CheckBox checkBox = new CheckBox(this).setBaseCls("x-grid-checkcolumn");
        if (!checkBox.waitToRender(500L, false)) {
            return null;
        }
        String attributeClass = checkBox.getAttributeClass();
        if (Strings.isNullOrEmpty(attributeClass)) {
            attributeClass = checkBox.getAttributeClass();
        }
        return attributeClass.contains("x-grid-checkcolumn-checked");
    }

    private void scrollInGrid(Cell cell) {
        while (!cell.waitToRender(100)) {
            Grid grid;
            try {
                grid = (Grid) getPathBuilder().getContainer();
            } catch (ClassCastException e) {
                grid = (Grid) getPathBuilder().getContainer().getPathBuilder().getContainer();
            }
            if (!grid.scrollPageDown()) {
                break;
            }
        }
    }
}