package com.easy.framework.skin.view_attr;

import java.util.ArrayList;
import java.util.List;

public enum AttrType {
    BACKGROUND("background"),
    SRC("src"),
    TEXT("text"),
    TYPE_FACE("typeface"),
    TEXT_COLOR("textColor"),
    DRAWABLE_LEFT("drawableLeft"),
    DRAWABLE_START("drawableStart"),
    DRAWABLE_TOP("drawableTop"),
    DRAWABLE_RIGHT("drawableRight"),
    DRAWABLE_END("drawableEnd"),
    DRAWABLE_BOTTOM("drawableBottom"),
    NO_SUPPORT("noSupport");
    public String type;

    AttrType(String type) {
        this.type = type;
    }

    public static List<IApply> assemble(List<SkinAttrParam> skinAttrParams) {
        DrawableAttr drawableAttr = null;
        List<IApply> attrs = new ArrayList<>();
        for (SkinAttrParam attrParam : skinAttrParams) {
            switch (attrParam.attrType) {
                case TEXT:
                    attrs.add(new TextAttr(TEXT, attrParam.id));
                    break;
                case TYPE_FACE:
                    attrs.add(new TypeFaceAttr(TYPE_FACE, attrParam.id));
                    break;
                case SRC:
                    attrs.add(new SrcAttr(SRC, attrParam.id));
                    break;
                case BACKGROUND:
                    attrs.add(new BackgroundAttr(BACKGROUND, attrParam.id));
                    break;
                case TEXT_COLOR:
                    attrs.add(new TextColorAttr(TEXT_COLOR, attrParam.id));
                    break;
                case DRAWABLE_LEFT:
                case DRAWABLE_START:
                    if (drawableAttr == null) {
                        drawableAttr = new DrawableAttr();
                    }
                    drawableAttr.setLef(attrParam.id);
                    break;
                case DRAWABLE_TOP:
                    if (drawableAttr == null) {
                        drawableAttr = new DrawableAttr();
                    }
                    drawableAttr.setTop(attrParam.id);
                    break;
                case DRAWABLE_RIGHT:
                case DRAWABLE_END:
                    if (drawableAttr == null) {
                        drawableAttr = new DrawableAttr();
                    }
                    drawableAttr.setRight(attrParam.id);
                    break;
                case DRAWABLE_BOTTOM:
                    if (drawableAttr == null) {
                        drawableAttr = new DrawableAttr();
                    }
                    drawableAttr.setBottom(attrParam.id);
                    break;
            }
        }
        if (drawableAttr != null) {
            attrs.add(drawableAttr);
        }
        return attrs;
    }

    public String value() {
        return type;
    }

    public static AttrType getType(String type) {
        for (AttrType attrType : values()) {
            if (attrType.value().equals(type)) {
                return attrType;
            }
        }
        return NO_SUPPORT;
    }
}
